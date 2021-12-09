package com.system.management.task.tests.commands.show;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.show.ShowAllTeamBoardsCommand;
import com.system.management.task.commands.show.ShowTeamMembersCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.exceptions.ElementNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.MemberImpl.VALID_MEMBER_NAME;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;

public class ShowTeamMembersCommand_Tests {


    private RepositoryHelper repoHelper;
    private Command command;

    @BeforeEach
    void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createMember(VALID_MEMBER_NAME, VALID_TEAM_NAME);
        repoHelper.createMember("member2", VALID_TEAM_NAME);
        command = new ShowTeamMembersCommand(repoHelper);
    }


    @Test
    public void ShowAllTeamBoards_Should_ShowTeamMembers_When_ValidParameters() {
        String actual = command.execute(Arrays.asList(VALID_TEAM_NAME));
        String str = String.format("Team %s\nMembers: \n%s\n%s",VALID_TEAM_NAME,VALID_MEMBER_NAME,"member2");
        Assertions.assertEquals(str, actual);
    }

    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {
        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(Arrays.asList("content", "2", "3")));
    }
}
