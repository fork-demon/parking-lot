package services.executor;

import services.facade.DispatcherRequestProcessor;
import services.facade.Event;
import services.facade.QueryRequestProcessor;
import services.factory.EventFactory;
import services.query.EventType;
import services.query.QueryType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractExecutor    {

    protected QueryRequestProcessor queryRequestProcessor;
    protected DispatcherRequestProcessor dispatcherRequestProcessor;

    public AbstractExecutor(QueryRequestProcessor queryRequestProcessor, DispatcherRequestProcessor dispatcherRequestProcessor) {
        this.queryRequestProcessor = queryRequestProcessor;
        this.dispatcherRequestProcessor = dispatcherRequestProcessor;
    }


    public void execute(String line) {
        if (null != line && !line.isEmpty()) {
            String[] command = line.split(" ");
            if (null != command && command.length > 0) {
                if (EventType.contains(command[0])) {
                    Event event = EventFactory.getEventType(command);
                    dispatcherRequestProcessor.process(event, null);
                } else if (QueryType.contains(command[0])) {
                    Map<String, String> args = null;
                    if(command.length > 1){
                        args = new HashMap<>();
                        args.put("criteria", command[1]);
                    }
                    queryRequestProcessor.process(command[0], args);

                }
                else if("exit".equals(command[0])){
                    System.exit(0);
                }
            }
        }
    }

    public abstract void run() throws IOException;
}
