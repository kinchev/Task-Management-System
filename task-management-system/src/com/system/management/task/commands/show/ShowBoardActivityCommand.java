package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ShowBoardActivityCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        return String.format("Team: %s\nBoard: %s\n%s", teamName, boardName, showBoardActivity(teamName, boardName));
    }

    private String showBoardActivity(String teamName, String boardName) {
        return getRepoHelper().findBoardByName(teamName, boardName).viewBoardActivity();
    }
}
