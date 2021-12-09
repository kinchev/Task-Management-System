package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Team;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.core.RepositoryConstants.NO_MEMBERS_IN_TEAM;


public class ShowTeamMembersCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowTeamMembersCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return showTeamMembers(teamName);
    }

    private String showTeamMembers(String teamName) {
        StringBuilder sb = new StringBuilder();
        Team team = getRepoHelper().findTeamByName(teamName);
        if (team.getMembers().size() == 0) {
            return sb.append(NO_MEMBERS_IN_TEAM).toString();
        }
        sb.append(String.format("Team %s\n", team.getName()));
        sb.append("Members: \n");
        team.getMembers()
                .forEach(member -> sb.append(String.format("%s\n", member.getName())));
        return sb.toString().trim();
    }
}
