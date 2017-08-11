package com.nightcat.event;

/**
 * Created by finderlo on 15/04/2017.
 * 订阅事件的执行器
 * @author Aollio
 */
public interface EventExecutor {
    void execute(Event event, Object context);
}
