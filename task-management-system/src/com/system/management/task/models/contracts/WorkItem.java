package com.system.management.task.models.contracts;

import com.system.management.task.models.enums.Status;

import java.util.List;

public interface WorkItem extends Identifiable, Printable {
    int getId();

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    Status getStatus();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    List<Comment> getComments();

    String printComments();

    List<Activity> getHistory();

    void changeStatus(Status status);

    String showHistory();

}
