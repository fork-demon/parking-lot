package services.executor;

import services.facade.DispatcherRequestProcessor;
import services.facade.QueryRequestProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveConsoleExecutor extends AbstractExecutor {

    public InteractiveConsoleExecutor(QueryRequestProcessor queryRequestProcessor, DispatcherRequestProcessor dispatcherRequestProcessor) {
        super(queryRequestProcessor, dispatcherRequestProcessor);
    }

    @Override
    public void run() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));


        while(true) {
            String inputString = bufferRead.readLine();
            execute(inputString);
        }
    }
}
