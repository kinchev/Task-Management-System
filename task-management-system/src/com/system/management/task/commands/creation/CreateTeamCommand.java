package com.system.management.task.commands.creation;

import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.CREATE_TEAM;

public class CreateTeamCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_TEAM = 1;

    public CreateTeamCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS_TEAM);
        String teamName = parameters.get(0);
        getRepoHelper().createTeam(teamName);
        return String.format(CREATE_TEAM, teamName);
    }

}
