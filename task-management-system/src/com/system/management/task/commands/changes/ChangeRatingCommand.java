package com.system.management.task.commands.changes;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.*;

public class ChangeRatingCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeRatingCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_MUST_BE_NUMBER);
        int rating = ParsingHelpers.tryParseInt(parameters.get(1), RATING_MUST_BE_NUMBER);
        changeFeedbackRating(id, rating);
        return String.format(CHANGE_RATING, id);
    }

    private void changeFeedbackRating(int id, int rating) {
        getRepoHelper().findElementById(id, getRepoHelper().getFeedbacks()).setRating(rating);
    }

}
