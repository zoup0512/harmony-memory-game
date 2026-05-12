package com.yandex.metrica.impl.ob;

import java.util.concurrent.Executor;

public class bu implements Executor {
    public void execute(Runnable command) {
        if (command != null) {
            command.run();
        }
    }
}
