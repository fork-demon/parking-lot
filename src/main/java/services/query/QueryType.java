package services.query;

import java.util.HashSet;
import java.util.Set;

public enum QueryType {

    STATUS("status"),
    REG_NOS_FOR_COLOR("registration_numbers_for_cars_with_colour"),
    SLOT_NOS_FOR_COLOR("slot_numbers_for_cars_with_colour"),
    SLOT_NOS_FOR_REG_NO("slot_number_for_registration_number");

    private String queryType;

    static Set<String> cache = new HashSet<>();

    static {
        for (QueryType queryType : QueryType.values()) {
            cache.add(queryType.getQueryType());
        }
    }

    public static boolean contains(String query) {
        return cache.contains(query);
    }

    QueryType(String queryType){
        this.queryType = queryType;
    }

    public String getQueryType() {
        return queryType;
    }
}
