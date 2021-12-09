package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ShowTasksCommand extends BaseCommand {

    public ShowTasksCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        List<Task> list = new ArrayList<>();
        list.addAll(getRepoHelper().getBugs());
        list.addAll(getRepoHelper().getStories());
        StringBuilder sb = new StringBuilder();
        Stream<Task> s = list.stream().sorted(Comparator.comparing(Task::getTitle));
        if (parameters.size() == 0) {
            s.forEach(task -> sb.append(task.getTitle()).append("\n"));
        } else {
            s.filter(task -> task.getTitle().equals(parameters.get(0))).forEach(task -> sb.append(task.getTitle()).append("\n"));
        }
        return sb.toString();
    }
}
