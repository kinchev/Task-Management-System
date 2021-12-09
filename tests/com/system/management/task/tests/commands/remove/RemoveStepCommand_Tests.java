package com.system.management.task.tests.commands.remove;

import com.system.management.task.commands.add.AddStepCommand;
import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.remove.RemoveStepCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;

public class RemoveStepCommand_Tests {
    private RepositoryHelper repoHelper;
    private Command command;

    @BeforeEach
    void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createBug(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.ACTIVE, Priority.HIGH, Severity.CRITICAL);
        command = new RemoveStepCommand(repoHelper);
    }
    @Test
    public void addStep_Should_RemoveStep_When_ValidParameters() {
        command.execute(Arrays.asList("step", "1"));
        Assertions.assertFalse(repoHelper.getBugs().get(0).getStepsToReproduce().contains("step"));
    }

    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(Arrays.asList("1")));
    }
}

