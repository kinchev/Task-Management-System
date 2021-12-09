package com.system.management.task.models;

import com.system.management.task.models.contracts.Activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ActivityImpl implements Activity {
    private static final String DESCRIPTION_ERR = "Description cannot be null";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");

    private String description;
    private final LocalDateTime timestamp;

    public ActivityImpl(String description) {
        setDescription(description);
        this.timestamp = LocalDateTime.now();

    }

    private void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException(DESCRIPTION_ERR);
        }
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String viewInfo() {
        return String.format("[%s] %s", timestamp.format(formatter), description);
    }

}
