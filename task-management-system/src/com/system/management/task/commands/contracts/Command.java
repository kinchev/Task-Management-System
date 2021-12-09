package com.system.management.task.commands.contracts;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);
}
