package com.system.management.task.tests.commands.show;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.show.ShowBoardActivityCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.exceptions.ElementNotFoundException;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.MemberImpl.VALID_MEMBER_NAME;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;

public class ShowBoardActivityCommandTests {
    private RepositoryHelper repoHelper;
    private Command command;

    @BeforeEach
    void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createStory(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.NOT_DONE, Priority.HIGH, Size.MEDIUM);
        repoHelper.createStory(VALID_TEAM_NAME, VALID_BOARD_NAME, "stoahahahy2", VALID_DESCRIPTION, Status.NOT_DONE, Priority.HIGH, Size.MEDIUM);
        command = new ShowBoardActivityCommand(repoHelper);
    }

    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(Arrays.asList("1", "2", "3")));
    }

    @Test
    public void ShowAllTeamBoards_Should_ShowBoardActivity_When_ValidParameters() {
        String actual = command.execute(Arrays.asList(VALID_TEAM_NAME, VALID_BOARD_NAME));
        Assertions.assertTrue(actual.contains("Story [xxxxxxxxxxx] was successfully added to board xxxxxx!"));
        Assertions.assertTrue(actual.contains("Story [stoahahahy2] was successfully added to board xxxxxx!"));
    }

}