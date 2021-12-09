package com.system.management.task.models;

import com.system.management.task.exceptions.InvalidStatusException;
import com.system.management.task.models.contracts.Feedback;
import com.system.management.task.models.enums.Status;

import static com.system.management.task.models.ModelConstants.INVALID_FEEDBACK_STATUS_INPUT;

public class FeedbackImpl extends WorkItemImpl implements Feedback {
    private int rating;

    public FeedbackImpl(int id, String title, String description, Status status, int rating) {
        super(id, title, description, status);
        this.rating = rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int getRating() {
        return this.rating;
    }

    @Override
    protected void validateStatus(Status status) {
        if (!status.toString().equals(Status.NEW.toString())
                && !status.toString().equalsIgnoreCase(Status.UNSCHEDULED.toString())
                && !status.toString().equalsIgnoreCase(Status.SCHEDULED.toString())
                && !status.toString().equalsIgnoreCase(Status.DONE.toString())) {
            throw new InvalidStatusException(INVALID_FEEDBACK_STATUS_INPUT);
        }
    }

    @Override
    public String getAsString() {
        return String.format("%sRating: [%d]", super.getAsString(), this.rating);
    }

    @Override
    public String toString() {
        return "Feedback";
    }
}
