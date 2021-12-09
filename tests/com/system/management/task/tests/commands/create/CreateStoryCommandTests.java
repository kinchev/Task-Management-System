package com.system.management.task.tests.commands.create;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.creation.CreateStoryCommand;
import com.system.management.task.commands.creation.CreateTeamCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.core.contracts.TaskManagementRepository;
import com.system.management.task.exceptions.ElementNotFoundException;
import com.system.management.task.exceptions.InvalidSeverityException;
import com.system.management.task.exceptions.InvalidSizeException;
import com.system.management.task.models.StoryImpl;
import com.system.management.task.models.contracts.Story;
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

public class CreateStoryCommandTests {
    private RepositoryHelper repoHelper;
    private Command command;


    @BeforeEach
    public void Before() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        this.command = new CreateStoryCommand(repoHelper);
    }

    @Test
    public void executeCommand_Should_validateParameters() {
        command.execute(Arrays.asList(VALID_BOARD_NAME, VALID_TEAM_NAME, VALID_TITLE, VALID_DESCRIPTION, "Done", "Low", "Small"));
        Assertions.assertFalse(repoHelper.getStories().contains(new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.LOW, Size.SMALL)));
    }

    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(Arrays.asList(VALID_TEAM_NAME, VALID_BOARD_NAME)));
    }
    @Test
    public void changeSize_Should_ThrowException_When_SizeIsInvalid() {
        Story s = new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.LOW, Size.SMALL);
        Assertions.assertThrows(InvalidSizeException.class,
                () -> s.changeSize(Size.SMALL));
    }
}