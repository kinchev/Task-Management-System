package com.system.management.task.commands.changes;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.CHANGE_BUG_PRIORITY;

public class ChangeBugPriorityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeBugPriorityCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), "Id must be a number");
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1).toLowerCase(), Priority.class);
        changeBugPriority(id, priority);
        return CHANGE_BUG_PRIORITY;
    }

    private void changeBugPriority(int id, Priority priority) {
        getRepoHelper().findElementById(id, getRepoHelper().getBugs()).changePriority(priority);
    }
}
