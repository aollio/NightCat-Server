package com.yemao.common.timing;

import com.yemao.common.base.BaseObject;
import com.yemao.utility.Util;
import com.yemao.entity.Task;
import com.yemao.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TimerImpl extends BaseObject implements Timer {

    private Map<TaskType, Executor> handlers = new HashMap<>();

    @Autowired
    private TaskRepository taskRepository;

    private Executor NULL_EXECUTOR = new Executor() {
        @Override
        public void run(String data, TaskType type) {
            logger.warn("执行任务没有对应的执行器. Type: " + type.toString() + ". Data: " + data);
        }
    };

    private boolean doing;

    private Thread execute_thread = null;
    private long interval = 10 * 1000;

    TimerImpl() {
        for (TaskType type : TaskType.values()) {
            handlers.put(type, NULL_EXECUTOR);
        }
    }

    /**
     * 递交一个任务到计时器
     */
    @Override
    public void sumbit(String data, TaskType type, int delay, TimeUnit timeUnit) {
        Task task = new Task();
        task.setType(type);
        task.setCreate_time(Util.now());
        task.setData(data);
        task.setStatus(Task.Status.Wait_execute);
        task.setExecute_time(new Timestamp(System.currentTimeMillis() + delay * timeUnit.getWeight()));
        taskRepository.save(task);
    }


    @Override
    public void register(TaskType type, Executor executor) {
        handlers.put(type, executor);
    }

    @Override
    public void start() {
        if (doing) {
            throw new RuntimeException("定时器线程已经开始执行了");
        }
        doing = true;
        execute_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (doing) {
                    try {
                        checkAndExecute();
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        logger.info("线程睡眠失败");
                        e.printStackTrace();
                    }
                }
            }
        });
        execute_thread.start();

    }

    /**
     * 轮询数据库, 查找小于当前时间的未完成的任务
     */
    private void checkAndExecute() {
        List<Task> tasks = taskRepository.findNeedExecute();
        for (Task task : tasks) {
            logger.info("开始执行定时任务: " + task.toString());
            try {
                Executor executor = handlers.get(task.getType());
                task.setExecute_time(Util.now());
                executor.run(task.getData(), task.getType());
                task.setStatus(Task.Status.Success);
            } catch (Exception e) {
                task.setStatus(Task.Status.Fail);
                logger.error("执行定时任务出现异常, " + task.toString(), e);
            } finally {
                task.setFinish_time(Util.now());
                taskRepository.update(task);
            }
        }
    }
}
