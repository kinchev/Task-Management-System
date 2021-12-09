package com.system.management.task.commands.changes;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.CHANGE_BUG_SEVERITY;

public class ChangeBugSeverityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeBugSeverityCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), "Id must be a number");
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(1).toLowerCase(), Severity.class);
        changeBugSeverity(id, severity);
        return CHANGE_BUG_SEVERITY;
    }

    private void changeBugSeverity(int id, Severity severity) {
        getRepoHelper().findElementById(id, getRepoHelper().getBugs()).changeSeverity(severity);
    }
}
