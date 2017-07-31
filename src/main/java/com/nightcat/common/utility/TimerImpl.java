package com.nightcat.common.utility;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author finderlo
 * @date 21/04/2017
 */
@Component
public class TimerImpl implements Timer, Runnable {


    private Map<Long, List<Task>> tasks = new HashMap<>(1000);

    private ExceptionHandler exceptionHandler;

    public TimerImpl() {
        this.exceptionHandler = new ExceptionHandler() {
            @Override
            public void handle(Throwable e, Task task) {
                //todo 定时器执行的异常处理器
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        // 每秒执行一次run()方法,非阻塞
        service.scheduleAtFixedRate(this, 1, 1, java.util.concurrent.TimeUnit.SECONDS);
    }

    @Override
    public void submit(Task task, int delay, TimeUnit unit) {
        //校对参数
        if (delay <= 0) {
            throw new IllegalArgumentException();
        }

        //秒为单位，获取当前时间
        long time = System.currentTimeMillis() / 1000;
        //计算截止时间
        time = time + delay * unit.getWeight();
        //将时间放入队列中
        List<Task> taskQueue = tasks.getOrDefault(time, new ArrayList<>());
        taskQueue.add(task);

    }

    @Override
    public void run() {
        long time = System.currentTimeMillis() / 1000;
        List<Task> task = tasks.remove(time);
        new Thread(new Executor(task,exceptionHandler)).run();
    }


    private static class Executor implements Runnable {

        List<Task> tasks;
        ExceptionHandler handler;

        Executor(List<Task> tasks,ExceptionHandler handler) {
            this.handler = handler;
            this.tasks = tasks;
        }

        public void run() {
            if (tasks == null) return;
            for (Task task : tasks) {
                try {
                    task.run();
                } catch (Exception e) {
                    //do nothing
                    //定时任务出现异常
                    handler.handle(e,task);
                }
            }
        }

    }

    public interface ExceptionHandler{
        void handle(Throwable e, Task task);
    }
}
