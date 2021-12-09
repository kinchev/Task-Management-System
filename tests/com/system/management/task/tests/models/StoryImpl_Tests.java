package com.system.management.task.tests.models;

import com.system.management.task.models.*;
import com.system.management.task.models.contracts.Story;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;

public class StoryImpl_Tests {

    private Story story;

    @BeforeEach
    void init() {
        this.story = new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
    }

    @Test
    public void StoryImpl_ShouldImplementStoryInterface() {
        Assertions.assertTrue(story instanceof Story);
    }

    @Test
    public void constructor_should_createStory_when_argumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> {
            new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
            Assertions.assertEquals(1, story.getId());
            Assertions.assertEquals(VALID_TITLE, story.getTitle());
            Assertions.assertEquals(VALID_DESCRIPTION, story.getDescription());
            Assertions.assertEquals(Status.DONE, story.getStatus());
            Assertions.assertEquals(Priority.HIGH, story.getPriority());
            Assertions.assertEquals(Size.MEDIUM, story.getSize());
        });
    }

    @Test
    public void changeSize_Should_ChangeSize() {
        story.changeSize(Size.SMALL);
        Assertions.assertEquals(Size.SMALL.toString(), story.getSize().toString());
    }

    @Test
    public void getSize_Should_GetSize() {
        Assertions.assertEquals(Size.MEDIUM.toString(), story.getSize().toString());
    }

    @Test
    public void setSize_Should_setSize() {
        story.setSize(Size.LARGE);
        Assertions.assertEquals(Size.LARGE.toString(), story.getSize().toString());
    }

    @Test
    public void toString_Should_PrintType() {
        Assertions.assertEquals("Story", story.toString());
    }

}
