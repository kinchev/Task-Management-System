package com.system.management.task.models;

import com.system.management.task.exceptions.InvalidStatusException;
import com.system.management.task.models.contracts.Activity;
import com.system.management.task.models.contracts.Comment;
import com.system.management.task.models.contracts.WorkItem;
import com.system.management.task.models.enums.Status;
import com.system.management.task.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.system.management.task.models.ModelConstants.*;

public abstract class WorkItemImpl implements WorkItem {

    private final int id;
    private String title;
    private String description;
    private Status status;
    private final List<Comment> comments;
    private final List<Activity> history;

    public WorkItemImpl(int id, String title, String description, Status status) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        setStatus(status);
        this.comments = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        validateInput(title.length(), TASK_TITLE_LEN_MIN, TASK_TITLE_LEN_MAX, TASK_TITLE_LEN_ERR);
        this.title = title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        validateInput(description.length(), TASK_DESCRIPTION_LEN_MIN, TASK_DESCRIPTION_LEN_MAX, TASK_DESCRIPTION_LEN_ERR);
        this.description = description;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    private void setStatus(Status status) {
        validateStatus(status);
        this.status = status;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
        Activity activity = new ActivityImpl(String.format("Comment [%s] was successfully added to workitem!", comment.getContent()));
        history.add(activity);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
        Activity activity = new ActivityImpl("Comment was successfully removed!");
        history.add(activity);
    }

    @Override
    public String printComments() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Task [%s] Comments: \n", this.title));
        this.comments.forEach(comment -> sb.append(comment.viewComment()).append("\n"));
        return sb.toString();
    }

    @Override
    public List<Comment> getComments() {
        return this.comments;
    }

    @Override
    public List<Activity> getHistory() {
        return this.history;
    }

    @Override
    public String showHistory() {
        if (history.size() == 0) {
            return String.format("No activity in work item #%d.", this.id);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("WorkItem %d\n", this.id));
        for (int i = 0; i < history.size(); i++) {
            if (i == history.size() - 1) {
                sb.append(history.get(i).viewInfo());
            } else {
                sb.append(history.get(i).viewInfo()).append("\n");
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String getAsString() {
        return String.format("Title: [%s], TaskID: %d, Description: [%s], Status: [%s], ", this.title, this.id, this.description, this.status);
    }

    @Override
    public void changeStatus(Status status) {
        Activity activity;
        Status copy = this.status;
        if (this.status.toString().equals(status.toString())) {
            activity = new ActivityImpl(String.format(CANNOT_CHANGE_STATUS, status.toString()));
            throw new InvalidStatusException(String.format(CANNOT_CHANGE_STATUS, status.toString()));
        }
        this.setStatus(status);
        activity = new ActivityImpl(String.format(STATUS_CHANGED, copy.toString(), status.toString()));
        this.history.add(activity);
    }

    private void validateInput(int length, int min, int max, String msg) {
        ValidationHelpers.validateIntRange(length, min, max, msg);
    }

    protected abstract void validateStatus(Status status);
}
