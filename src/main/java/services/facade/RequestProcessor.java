package services.facade;

import java.util.Map;

public interface RequestProcessor <E> {

    public void process(E request, Map<String,String> args);


}
