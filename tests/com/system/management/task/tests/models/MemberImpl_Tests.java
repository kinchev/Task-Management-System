package com.system.management.task.tests.models;

import com.system.management.task.models.*;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.models.contracts.Story;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.system.management.task.tests.utils.TestData.MemberImpl.VALID_MEMBER_NAME;

public class MemberImpl_Tests {
    private Member m;

    @BeforeEach
    void init() {
        this.m = new MemberImpl("memberName");
    }

    @Test
    public void MemberImpl_ShouldImplementMemberInterface() {
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Assertions.assertTrue(member instanceof Member);
    }

    @Test
    public void constructor_Should_CreateMember_When_ArgumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> {
            new MemberImpl("memberName");
            Assertions.assertEquals("memberName", m.getName());
        });
    }

    @Test
    public void constructor_Should_ThrowException_When_nameIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new MemberImpl(""));
    }
    @Test
    public void addTask_Should_AddTask() {
        Story s = new StoryImpl(1, "title_title", "description", Status.DONE, Priority.HIGH, Size.MEDIUM);
        m.addTask(s);
        Assertions.assertEquals(1, m.getTasks().size());
        Assertions.assertEquals(1, m.getHistory().size());
    }
    @Test
    public void setName_Should_setName() {
        m.setName("Testname");
        Assertions.assertEquals("Testname", m.getName());
    }
}
