package com.system.management.task.models;

import com.system.management.task.exceptions.InvalidSeverityException;
import com.system.management.task.exceptions.InvalidStatusException;
import com.system.management.task.models.contracts.Activity;
import com.system.management.task.models.contracts.Bug;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

import static com.system.management.task.models.ModelConstants.*;

public class BugImpl extends TaskBase implements Bug {

    private Severity severity;
    private final List<String> stepsToReproduce;

    public BugImpl(int id, String title, String description, Status status, Priority priority, Severity severity) {
        super(id, title, description, status, priority);
        setSeverity(severity);
        this.stepsToReproduce = new ArrayList<>();
    }

    @Override
    public Severity getSeverity() {
        return this.severity;
    }

    @Override
    public void setSeverity(Severity severity) {
        validateSeverity(severity);
        this.severity = severity;
    }

    @Override
    public void changeSeverity(Severity severity) {
        Activity activity;
        Severity copy = this.severity;
        if (this.severity.toString().equalsIgnoreCase(severity.toString())) {
            activity = new ActivityImpl(String.format(CANNOT_CHANGE_SEVERITY, severity));
            super.getHistory().add(activity);
            throw new InvalidSeverityException(String.format(CANNOT_CHANGE_SEVERITY, severity));
        }
        this.setSeverity(severity);
        activity = new ActivityImpl(String.format(SEVERITY_CHANGE, copy, severity));
        super.getHistory().add(activity);
    }

    @Override
    public List<String> getStepsToReproduce() {
        return this.stepsToReproduce;
    }

    @Override
    public String printStepsToReproduce() {
        if (this.stepsToReproduce.size() == 0) return NO_STEPS;

        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (int i = 0; i < stepsToReproduce.size(); i++) {
            sb.append(String.format("%d. %s\n", counter++, stepsToReproduce.get(i)));
        }
        return sb.toString().trim();
    }

    @Override
    public void addStepsToReproduce(String step) {
        this.stepsToReproduce.add(step);
    }

    private void validateSeverity(Severity severity) {
        if (!severity.toString().equalsIgnoreCase(Severity.MINOR.toString())
                && !severity.toString().equalsIgnoreCase(Severity.MAJOR.toString())
                && !severity.toString().equalsIgnoreCase(Severity.CRITICAL.toString())) {
            throw new InvalidSeverityException(INVALID_BUG_SEVERITY_INPUT);
        }
    }

    @Override
    protected void validateStatus(Status status) {
        if (!status.toString().equalsIgnoreCase(Status.ACTIVE.toString())
                && !status.toString().equalsIgnoreCase(Status.FIXED.toString())) {
            throw new InvalidStatusException(INVALID_BUG_STATUS_INPUT);
        }
    }

    @Override
    public String getAsString() {
        return String.format("%Severity: [%s]", super.getAsString(), this.severity.toString());
    }

    @Override
    public String toString() {
        return "Bug";
    }
}

