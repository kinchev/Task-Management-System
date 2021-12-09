package com.system.management.task.commands.add;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.ADD_STEP;
import static com.system.management.task.commands.CommandConstants.ID_MUST_BE_NUMBER;

public class AddStepCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public AddStepCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String step = parameters.get(0);
        int id = ParsingHelpers.tryParseInt(parameters.get(1), ID_MUST_BE_NUMBER);
        addStep(id, step);
        return String.format(ADD_STEP, step);
    }

    private void addStep(int id, String step) {
        getRepoHelper().findElementById(id, getRepoHelper().getBugs()).addStepsToReproduce(step);
    }

}
