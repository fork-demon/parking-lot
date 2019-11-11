package services.facade;

import java.util.Map;

public class DispatcherRequestProcessor implements RequestProcessor<Event> {

    private EventDispatcher eventDispatcher;

    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void process(Event request, Map<String, String> args) {
        eventDispatcher.dispatch(request);
    }
}
