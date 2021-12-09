package com.system.management.task.commands.changes;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.CHANGE_STORY_PRIORITY;
import static com.system.management.task.commands.CommandConstants.ID_MUST_BE_NUMBER;

public class ChangeStoryPriorityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeStoryPriorityCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_MUST_BE_NUMBER);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
        changeStoryPriority(id, priority);
        return CHANGE_STORY_PRIORITY;
    }

    private void changeStoryPriority(int id, Priority priority) {
        getRepoHelper().findElementById(id, getRepoHelper().getStories()).setPriority(priority);
    }
}
