package com.system.management.task.tests.commands.create;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.creation.CreateTeamCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.system.management.task.commands.creation.CreateTeamCommand.EXPECTED_NUMBER_OF_ARGUMENTS_TEAM;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;
import static com.system.management.task.tests.utils.TestUtilities.initializeListWithSize;

public class CreateTeamCommandTests {

    private RepositoryHelper repoHelper;
    private Command command;
    private List<String> parameters;

    @BeforeEach
    public void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        command = new CreateTeamCommand(repoHelper);
        parameters= new ArrayList<>();
    }
    @Test
    public void execute_should_throwException_when_missingParameters() {
       parameters=initializeListWithSize(EXPECTED_NUMBER_OF_ARGUMENTS_TEAM - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }
    @Test
    public void executeCommand_Should_createTeam_whenArgumentsAreValid() {
        command.execute(Arrays.asList(VALID_TEAM_NAME));
        Team team = repoHelper.findTeamByName(VALID_TEAM_NAME);
        Assertions.assertTrue(team.getName().equals(VALID_TEAM_NAME));
    }

}