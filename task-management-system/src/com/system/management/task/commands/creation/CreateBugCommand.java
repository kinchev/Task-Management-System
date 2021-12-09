package com.system.management.task.commands.creation;

import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Status;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.CREATE_BUG;

public class CreateBugCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_BUG = 7;

    public CreateBugCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS_BUG);
        String team = parameters.get(0);
        String board = parameters.get(1);
        String title = parameters.get(2);
        String description = parameters.get(3);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(4).toUpperCase(), Status.class);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(5).toUpperCase(), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(6).toUpperCase(), Severity.class);
        getRepoHelper().createBug(team, board, title, description, status, priority, severity);
        return String.format(CREATE_BUG, title);
    }
}
