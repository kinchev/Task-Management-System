package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.ID_MUST_BE_NUMBER;


public class ShowWorkItemActivity extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowWorkItemActivity(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_MUST_BE_NUMBER);
        return showWorkItemActivity(id);
    }

    private String showWorkItemActivity(int id) {
        return getRepoHelper().findWorkItemById(id).showHistory();
    }
}
