package com.system.management.task.commands.creation;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.core.RepositoryHelper;

import java.util.List;

public abstract class BaseCommand implements Command {

    private final RepositoryHelper repoHelper;

    protected BaseCommand(RepositoryHelper repoHelper) {
        this.repoHelper = repoHelper;
    }

    protected RepositoryHelper getRepoHelper() {
        return repoHelper;
    }

    @Override
    public String execute(List<String> parameters) {
        return executeCommand(parameters);
    }

    protected abstract String executeCommand(List<String> parameters);

}
