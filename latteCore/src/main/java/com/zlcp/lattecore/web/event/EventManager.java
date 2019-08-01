package com.zlcp.lattecore.web.event;

import androidx.annotation.NonNull;

import java.util.HashMap;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 13:39
 * Note：
 */
public class EventManager {
    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    //惰性单例
    private static final class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event){
        EVENTS.put(name,event);
        return this;
    }

    public Event createEvent(@NonNull String action){
        final Event event = EVENTS.get(action);
        if (event ==null) {
            return new UndefineEvent();
        }
        return event;
    }

}
