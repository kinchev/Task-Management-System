package com.system.management.task.commands.creation;

import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.enums.Status;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.RATING_MUST_BE_NUMBER;

public class CreateFeedbackCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS_FEEDBACK = 6;

    public CreateFeedbackCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS_FEEDBACK);
        String team = parameters.get(0);
        String board = parameters.get(1);
        String title = parameters.get(2);
        String description = parameters.get(3);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(4), Status.class);
        int rating = ParsingHelpers.tryParseInt(parameters.get(5), RATING_MUST_BE_NUMBER);
        getRepoHelper().createFeedback(team, board, title, description, status, rating);
        return "Feedback created successfully!";
    }
}
