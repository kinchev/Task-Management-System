package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.ID_MUST_BE_NUMBER;
import static com.system.management.task.commands.CommandConstants.SHOW_STEPS;

public class ShowStepsCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowStepsCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_MUST_BE_NUMBER);
        String steps = showSteps(id);
        return String.format(SHOW_STEPS, id, steps);
    }

    private String showSteps(int id) {
        return getRepoHelper().findElementById(id, getRepoHelper().getBugs()).printStepsToReproduce();
    }
}
