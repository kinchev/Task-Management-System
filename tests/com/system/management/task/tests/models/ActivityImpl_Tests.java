package com.system.management.task.tests.models;

import com.system.management.task.models.ActivityImpl;
import com.system.management.task.models.TeamImpl;
import com.system.management.task.models.contracts.Activity;
import com.system.management.task.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActivityImpl_Tests {
    @Test
    public void TeamImpl_ShouldImplementTaskInterface() {
        ActivityImpl activity = new ActivityImpl("description");

        Assertions.assertTrue(activity instanceof Activity);
    }

    @Test
    public void constructor_should_createActivity_when_argumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> {
            String test = "Activity_description";
            Activity act = new ActivityImpl(test);
            Assertions.assertEquals(test, act.getDescription());
        });
    }
    @Test
    public void constructor_should_throwException_whenDescriptionIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ActivityImpl(null));
    }

}
