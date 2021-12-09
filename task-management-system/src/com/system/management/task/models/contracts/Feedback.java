package com.system.management.task.models.contracts;

public interface Feedback extends WorkItem, Identifiable {
    int getId();

    int getRating();

    void setRating(int rating);
}
