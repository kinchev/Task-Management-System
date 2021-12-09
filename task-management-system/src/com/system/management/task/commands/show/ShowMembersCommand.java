package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Team;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.SHOW_MEMBERS;

public class ShowMembersCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowMembersCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }


    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String members = showMembers(teamName);
        return String.format(SHOW_MEMBERS, teamName, members);
    }

    private String showMembers(String teamName) {
        Team team = getRepoHelper().findTeamByName(teamName);
        StringBuilder sb = new StringBuilder();
        team.getMembers()
                .forEach(m -> sb.append(String.format("%s%n", m.getName())));
        return sb.toString().trim();
    }

}
