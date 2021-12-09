package com.system.management.task.models;

import com.system.management.task.exceptions.InvalidSizeException;
import com.system.management.task.exceptions.InvalidStatusException;
import com.system.management.task.models.contracts.Activity;
import com.system.management.task.models.contracts.Story;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;

import static com.system.management.task.models.ModelConstants.*;

public class StoryImpl extends TaskBase implements Story {

    private Size size;

    public StoryImpl(int id, String title, String description, Status status, Priority priority, Size size) {
        super(id, title, description, status, priority);
        setSize(size);
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public void setSize(Size size) {
        validateSize(size);
        this.size = size;
    }

    @Override
    public void changeSize(Size size) {
        Activity activity;
        Size copy = this.size;
        if (this.size.toString().equalsIgnoreCase(size.toString())) {
            activity = new ActivityImpl(String.format(CANNOT_CHANGE_SIZE, size));
            super.getHistory().add(activity);
            throw new InvalidSizeException(String.format(CANNOT_CHANGE_SIZE, size));
        }
        this.setSize(size);
        activity = new ActivityImpl(String.format(SIZE_CHANGED, copy, size));
        super.getHistory().add(activity);
    }

    @Override
    protected void validateStatus(Status status) {
        if (!status.toString().equalsIgnoreCase(Status.NOT_DONE.toString())
                && !status.toString().equalsIgnoreCase(Status.IN_PROGRESS.toString())
                && !status.toString().equalsIgnoreCase(Status.DONE.toString())) {
            throw new InvalidStatusException(INVALID_STORY_STATUS_INPUT);
        }
    }

    private void validateSize(Size size) {
        if (!size.toString().equalsIgnoreCase(Size.LARGE.toString())
                && !size.toString().equalsIgnoreCase(Size.MEDIUM.toString())
                && !size.toString().equalsIgnoreCase(Size.SMALL.toString())) {
            throw new InvalidSizeException(INVALID_STORY_SIZE_INPUT);
        }
    }

    @Override
    public String getAsString() {
        return String.format("%sSize: [%s]", super.getAsString(), this.size.toString());
    }

    @Override
    public String toString() {
        return "Story";
    }
}

