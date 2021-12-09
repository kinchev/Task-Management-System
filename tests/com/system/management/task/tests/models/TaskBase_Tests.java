package com.system.management.task.tests.models;

import com.system.management.task.exceptions.InvalidPriorityException;
import com.system.management.task.models.MemberImpl;
import com.system.management.task.models.StoryImpl;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.models.contracts.Story;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskBase_Tests {

    private Story s;

    @BeforeEach
    void init() {
        this.s = new StoryImpl(1, "storyTitle", "description", Status.DONE, Priority.HIGH, Size.MEDIUM);
    }

    @Test
    public void getAssignee_Should_getAssignee() {
        Member m = new MemberImpl("MemberName");
        s.setAssignee(m);
        Assertions.assertEquals(m, s.getAssignee());
    }

    @Test
    public void setPriority_Should_setPriority() {
        s.setPriority(Priority.LOW);
        Assertions.assertEquals(Priority.LOW.toString(), s.getPriority().toString());
    }

    @Test
    public void changePriority_Should_ChangePriority() {
        s.changePriority(Priority.MEDIUM);
        Assertions.assertEquals(1, s.getHistory().size());
        Assertions.assertEquals(Priority.MEDIUM.toString(), s.getPriority().toString());
    }

    @Test
    public void changePriority_Should_ThrowException_When_PriorityIsInvalid() {
        Assertions.assertThrows(InvalidPriorityException.class,
                () -> s.changePriority(Priority.HIGH));
    }
}
