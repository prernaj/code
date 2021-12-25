package spring.project.logger;

import spring.project.logger.modules.LoggingModule;
import com.google.inject.Injector;
import com.google.inject.Guice;
import spring.project.logger.modules.PropertiesModule;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PropertiesModule(), new LoggingModule());
        final TaskManager taskManager = injector.getInstance(TaskManager.class);
        try {
            taskManager.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}