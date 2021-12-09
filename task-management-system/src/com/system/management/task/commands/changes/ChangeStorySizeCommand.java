package com.system.management.task.commands.changes;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.enums.Size;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.ID_MUST_BE_NUMBER;
import static com.system.management.task.commands.CommandConstants.STORY_SIZE_CREATED_MESSAGE;

public class ChangeStorySizeCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeStorySizeCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_MUST_BE_NUMBER);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(1).toLowerCase(), Size.class);
        changeStorySize(id, size);
        return String.format(STORY_SIZE_CREATED_MESSAGE);
    }

    private void changeStorySize(int id, Size story) {
        getRepoHelper().findElementById(id, getRepoHelper().getStories()).setSize(story);
    }
}
