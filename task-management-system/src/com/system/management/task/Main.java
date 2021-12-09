package com.system.management.task;

import com.system.management.task.core.TaskEngineImpl;

public class Main {

    public static void main(String[] args) {
        TaskEngineImpl engine = new TaskEngineImpl();
        engine.start();
    }
}