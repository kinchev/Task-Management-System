package com.system.management.task.tests.commands.change;

import com.system.management.task.commands.changes.ChangeBugPriorityCommand;
import com.system.management.task.commands.changes.ChangeStoryPriorityCommand;
import com.system.management.task.commands.contracts.Command;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;

public class ChangeStoryPriorityCommand_Tests {
    private RepositoryHelper repoHelper;
    private Command command;

    @BeforeEach
    void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createStory(VALID_TEAM_NAME,VALID_BOARD_NAME,VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.SMALL );
        command = new ChangeStoryPriorityCommand(repoHelper);
    }

    @Test
    public void addComment_Should_AddComment_When_ValidParameters() {
        command.execute(Arrays.asList("1", "Low"));
        Assertions.assertEquals(repoHelper.getStories().get(0).getPriority().toString(), "Low");
    }

    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(Arrays.asList("content")));
    }
}
