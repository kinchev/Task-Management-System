package com.system.management.task.commands.creation;

import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.CREATE_STORY;

public class CreateStoryCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS_STORY = 7;

    public CreateStoryCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS_STORY);
        String team = parameters.get(0);
        String board = parameters.get(1);
        String title = parameters.get(2);
        String description = parameters.get(3);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(4).toUpperCase(), Status.class);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(5).toUpperCase(), Priority.class);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(6).toUpperCase(), Size.class);
        getRepoHelper().createStory(team, board, title, description, status, priority, size);
        return String.format(CREATE_STORY, title);
    }
}
