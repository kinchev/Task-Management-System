package com.system.management.task.tests.commands.create;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.creation.CreateMemberCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.models.MemberImpl;
import com.system.management.task.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.system.management.task.tests.utils.TestData.MemberImpl.VALID_MEMBER_NAME;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;

public class CreateMemberCommandTests {
    private RepositoryHelper repoHelper;
    private Command command;


    @BeforeEach
    public void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        command = new CreateMemberCommand(repoHelper);
    }

    @Test
    public void executeCommand_Should_createMember_whenArgumentsAreValid() {
        command.execute(Arrays.asList(VALID_MEMBER_NAME, VALID_TEAM_NAME));
        Team team = repoHelper.findTeamByName(VALID_TEAM_NAME);
        Assertions.assertTrue(team.getMembers().contains(new MemberImpl(VALID_MEMBER_NAME)));
    }

    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(Arrays.asList("member")));
    }

}
