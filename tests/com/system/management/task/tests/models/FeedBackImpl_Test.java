package com.system.management.task.tests.models;

import com.system.management.task.models.FeedbackImpl;
import com.system.management.task.models.contracts.Feedback;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;

public class FeedBackImpl_Test {

    private Feedback feedback;

    @BeforeEach
    void init() {
        this.feedback = new FeedbackImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, 10);
    }

    @Test
    public void FeedbackImpl_ShouldImplementFeedbackInterface() {
        Assertions.assertTrue(feedback instanceof Feedback);
    }

    @Test
    public void constructor_should_createFeedback_when_argumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> {
            new FeedbackImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, 10);
            Assertions.assertAll(() -> {
                Assertions.assertEquals(1, feedback.getId());
                Assertions.assertEquals(VALID_TITLE, feedback.getTitle());
                Assertions.assertEquals(VALID_DESCRIPTION, feedback.getDescription());
                Assertions.assertEquals(Status.DONE, feedback.getStatus());
                Assertions.assertEquals(10, feedback.getRating());
            });
        });
    }
    @Test
    public void toString_Should_PrintType() {
        Assertions.assertEquals("Feedback", feedback.toString());
    }
    @Test
    public void getRating_Should_getRating() {
        Assertions.assertEquals(10, feedback.getRating());
    }
    @Test
    public void setRating_Should_setRating() {
        feedback.setRating(1);
        Assertions.assertEquals(1, feedback.getRating());
    }
}
