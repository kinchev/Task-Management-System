package com.system.management.task.models.contracts;

import java.util.List;

public interface Board {
    String getName();

    void addWorkItem(WorkItem i);

    void removeWorkItem(WorkItem i);

    List<WorkItem> getWorkItems();

    List<Activity> getActivityHistory();

    String viewBoardActivity();
}
