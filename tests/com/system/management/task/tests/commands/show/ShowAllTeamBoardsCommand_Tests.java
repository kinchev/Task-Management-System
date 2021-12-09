package com.system.management.task.tests.commands.show;

import com.system.management.task.commands.changes.ChangeBugPriorityCommand;
import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.show.ShowAllTeamBoardsCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.exceptions.ElementNotFoundException;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;

public class ShowAllTeamBoardsCommand_Tests {
    private RepositoryHelper repoHelper;
    private Command command;

    @BeforeEach
    void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        command = new ShowAllTeamBoardsCommand(repoHelper);
    }

    @Test
    public void ShowAllTeamBoards_Should_ShowTeamBoards_When_ValidParameters() {
        command.execute(Arrays.asList(VALID_TEAM_NAME));
        Assertions.assertEquals(VALID_BOARD_NAME, repoHelper.findTeamByName(VALID_TEAM_NAME).printBoards());
    }

    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(Arrays.asList("content", "2","3")));
    }
}
