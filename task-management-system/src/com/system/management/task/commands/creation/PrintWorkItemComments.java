package com.system.management.task.commands.creation;

import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.WorkItem;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

public class PrintWorkItemComments extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public PrintWorkItemComments(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), "Id must be a number");
        return printWorkItemComments(id);
    }

    private String printWorkItemComments(int id) {
        WorkItem item = super.getRepoHelper().findWorkItemById(id);
        return item.printComments();
    }
}