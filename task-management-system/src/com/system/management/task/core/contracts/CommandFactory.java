package com.system.management.task.core.contracts;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.core.RepositoryHelper;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString, RepositoryHelper repoHelper);
}

