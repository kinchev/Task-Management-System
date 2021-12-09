package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Team;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.core.RepositoryConstants.NO_BOARD_IN_TEAM;

public class ShowAllTeamBoardsCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowAllTeamBoardsCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return showAllTeamBoards(teamName);
    }

    private String showAllTeamBoards(String nameTeam) {
        Team team = getRepoHelper().findTeamByName(nameTeam);
        if (team.getBoards().size() == 0) {
            return NO_BOARD_IN_TEAM;
        }
        return String.format("Team: %s\nBoards:\n%s", team.getName(), team.printBoards());
    }
}
