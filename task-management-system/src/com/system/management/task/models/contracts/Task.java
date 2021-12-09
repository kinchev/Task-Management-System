package com.system.management.task.models.contracts;

import com.system.management.task.models.enums.Priority;

public interface Task extends WorkItem, Identifiable, Assignable {
    Member getAssignee();

    int getId();

    void setAssignee(Member assignee);

    Priority getPriority();

    void setPriority(Priority priority);

    void changePriority(Priority p);
}