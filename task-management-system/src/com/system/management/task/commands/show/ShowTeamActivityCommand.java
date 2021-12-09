package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.models.contracts.Team;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamActivityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowTeamActivityCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return showTeamActivity(teamName);
    }

    private String showTeamActivity(String teamName) {
        Team team = getRepoHelper().findTeamByName(teamName);
        StringBuilder sb = new StringBuilder();
        for (Member t : team.getMembers()) {
            sb.append(t.getActivityHistory());
        }
        return sb.toString().trim();
    }
}
