package com.system.management.task.tests.models;

import com.system.management.task.exceptions.InvalidSeverityException;
import com.system.management.task.exceptions.InvalidStatusException;
import com.system.management.task.models.BugImpl;
import com.system.management.task.models.contracts.Bug;
import com.system.management.task.models.contracts.Task;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;

public class BugImpl_Tests {

    private Bug bug;

    @BeforeEach
    void init() {
        this.bug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.ACTIVE, Priority.LOW, Severity.CRITICAL);
    }

    @Test
    public void BugImpl_ShouldImplementBugInterface() {
        Assertions.assertTrue(bug instanceof Bug);
    }

    @Test
    public void BugImpl_Should_ImplementTaskInterface() {
        Assertions.assertTrue(bug instanceof Task);
    }

    @Test
    public void constructor_Should_createBug_When_argumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> {
            new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.ACTIVE, Priority.LOW, Severity.CRITICAL);
            Assertions.assertAll(() -> {
                Assertions.assertEquals(1, bug.getId());
                Assertions.assertEquals(VALID_TITLE, bug.getTitle());
                Assertions.assertEquals(VALID_DESCRIPTION, bug.getDescription());
                Assertions.assertEquals(Status.ACTIVE, bug.getStatus());
                Assertions.assertEquals(Priority.LOW, bug.getPriority());
                Assertions.assertEquals(Severity.CRITICAL, bug.getSeverity());
            });
        });
    }

    @Test
    public void toString_Should_PrintType() {
        Assertions.assertEquals("Bug", bug.toString());
    }

    @Test
    public void addStepsToReproduce_Should_AddSteps() {
        bug.addStepsToReproduce("Step1");
        Assertions.assertEquals(1, bug.getStepsToReproduce().size());
    }

    @Test
    public void printStepsToReproduce_Should_PrintSteps() {
        bug.addStepsToReproduce("Step1");
        bug.addStepsToReproduce("Step2");
        String s = "1. Step1\n2. Step2";
        Assertions.assertEquals(s, bug.printStepsToReproduce());
    }

    @Test
    public void changeSeverity_Should_ChangeSeverity() {
        bug.changeSeverity(Severity.MAJOR);
        Assertions.assertEquals(Severity.MAJOR.toString(), bug.getSeverity().toString());
    }

    @Test
    public void getSeverity_Should_GetSeverity() {
        Assertions.assertEquals(Severity.CRITICAL.toString(), bug.getSeverity().toString());
    }

    @Test
    public void validateStatus_Should_ThrowException_WhenInvalidStatus() {
        Assertions.assertThrows(InvalidStatusException.class, () -> new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.LOW, Severity.CRITICAL));
    }

}
