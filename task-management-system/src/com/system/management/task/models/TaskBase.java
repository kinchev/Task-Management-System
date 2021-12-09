package com.system.management.task.models;

import com.system.management.task.exceptions.InvalidPriorityException;
import com.system.management.task.models.contracts.Activity;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.models.contracts.Task;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Status;

import static com.system.management.task.models.ModelConstants.*;

public abstract class TaskBase extends WorkItemImpl implements Task {

    private Member assignee;
    private Priority priority;

    public TaskBase(int id, String title, String description, Status status, Priority priority) {
        super(id, title, description, status);
        setPriority(priority);
    }

    @Override
    public Member getAssignee() {
        return this.assignee;
    }

    @Override
    public void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public void setPriority(Priority priority) {
        validatePriority(priority);
        this.priority = priority;
    }

    @Override
    public void changePriority(Priority priority) {
        Activity activity;
        Priority copy = this.priority;
        if (this.priority.toString().equals(priority.toString())) {
            activity = new ActivityImpl(String.format(CANNOT_CHANGE_PRIORITY, priority.toString()));
            super.getHistory().add(activity);
            throw new InvalidPriorityException(String.format(CANNOT_CHANGE_PRIORITY, priority.toString()));
        }
        this.setPriority(priority);
        activity = new ActivityImpl(String.format(PRIORITY_CHANGED, copy.toString(), priority.toString()));
        super.getHistory().add(activity);
    }

    @Override
    public String getAsString() {
        return String.format("%sPriority: [%s], ", super.getAsString(), this.priority.toString());
    }

    private void validatePriority(Priority priority) {
        if (!priority.toString().equalsIgnoreCase(Priority.HIGH.toString())
                && !priority.toString().equalsIgnoreCase(Priority.LOW.toString())
                && !priority.toString().equalsIgnoreCase(Priority.MEDIUM.toString())) {
            throw new InvalidPriorityException(INVALID_PRIORITY_INPUT);
        }
    }
}
