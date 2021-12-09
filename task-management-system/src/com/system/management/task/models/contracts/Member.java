package com.system.management.task.models.contracts;

import java.util.List;

public interface Member {
    String getName();

    void setName(String name);

    void addTask(Task task);

    List<Task> getTasks();

    List<Activity> getHistory();

    String getActivityHistory();
}
