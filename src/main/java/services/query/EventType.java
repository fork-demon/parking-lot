package services.query;

import java.util.HashSet;
import java.util.Set;

public enum EventType {

    PARK("park"),
    LEAVE("leave"),
    CREATE_PARKING_LOT("create_parking_lot");

    static Set<String> cache = new HashSet<>();

    static {
        for (EventType eventType : EventType.values()) {
            cache.add(eventType.getEventType());
        }
    }

    public static boolean contains(String event) {
        return cache.contains(event);
    }

    private String eventType;

    EventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}
