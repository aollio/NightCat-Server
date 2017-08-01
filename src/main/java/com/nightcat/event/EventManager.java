package com.nightcat.event;

/**
 * @author Aollio
 * @date 17/04/2017
 */
public interface EventManager {
    void publish(Event event, EventContext context);

    void register(Event event, EventExecutor executor);
}
