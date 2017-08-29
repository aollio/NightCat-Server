package com.yemao.common.timing;

/**
 * 定时器, 定时执行一个任务.
 * 注意每种类型的任务只能有一个执行器
 */
public interface Timer {
    void sumbit(String data, TaskType type, int delay, TimeUnit timeUnit);

    void register(TaskType type, Executor executor);

    void start();
}
