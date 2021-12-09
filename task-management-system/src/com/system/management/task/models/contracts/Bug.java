package com.system.management.task.models.contracts;

import com.system.management.task.models.enums.Severity;

import java.util.List;

public interface Bug extends Task {
    void setSeverity(Severity severity);

    Severity getSeverity();

    void changeSeverity(Severity s);

    List<String> getStepsToReproduce();

    String printStepsToReproduce();

    void addStepsToReproduce(String step);
}
