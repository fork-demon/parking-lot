package services.executor;

import services.facade.DispatcherRequestProcessor;
import services.facade.QueryRequestProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileExecutor extends AbstractExecutor {

    String filePath;

    public FileExecutor(QueryRequestProcessor queryRequestProcessor, DispatcherRequestProcessor dispatcherRequestProcessor, String filePath) {
        super(queryRequestProcessor, dispatcherRequestProcessor);
        this.filePath = filePath;
    }

    @Override
    public void run() throws IOException {
        File inputFile = new File(filePath);
        @SuppressWarnings("resource")
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = br.readLine()) != null) {
            execute(line);
        }
    }
}
