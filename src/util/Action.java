package util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.LinkedList;

public class Action {

    public static LinkedList<Service> services = new LinkedList<>();

    public static Service actOnce(Runnable backgroundRunnable, Runnable guiRunnable) {
        Service<Integer> service = new Service<>() {
            @Override
            protected Task<Integer> createTask() {
                Task task = new Task() {
                    @Override
                    protected Object call() throws Exception {
                        if (isCancelled()) return null;
                        backgroundRunnable.run();
                        return 1;
                    }
                };

                if (guiRunnable != null) task.setOnSucceeded(event -> guiRunnable.run());
                task.exceptionProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        getException().printStackTrace();
                    }
                });
                return task;
            }
        };
        service.exceptionProperty().addListener(new ChangeListener <Throwable>() {
            @Override
            public void changed(ObservableValue <? extends Throwable> observable, Throwable oldValue, Throwable newValue) {
                service.getException().printStackTrace();
            }
        });
        services.add(service);
        return service;
    }

}
