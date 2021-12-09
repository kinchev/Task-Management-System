package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Task;
import com.system.management.task.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ShowAssignablesCommand extends BaseCommand {
    private final String statusFlag = "-s";
    private final String assigneeFlag = "-a";

    public ShowAssignablesCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCountIsEvenAndMax4(parameters);
        String status = "";
        String assignee = "";
        for (int i = 0; i < parameters.size() - 1; i++) {
            if (parameters.get(i).equals(statusFlag)) {
                status = parameters.get(i + 1);
            } else if (parameters.get(i).equals(assigneeFlag)) {
                assignee = parameters.get(i + 1);
            }
        }
        List<Task> list = new ArrayList<>();
        list.addAll(getRepoHelper().getBugs());
        list.addAll(getRepoHelper().getStories());
        Stream<Task> tasks = list.stream();
        if (!status.equals("")) {
            String finalStatus = status;
            tasks = tasks.filter(t -> t.getStatus().toString().equals(finalStatus));
        }
        if (!assignee.equals("")) {
            String finalAssignee = assignee;
            tasks = tasks.filter(t -> Objects.nonNull(t.getAssignee())).filter(t -> t.getAssignee().getName().equals(finalAssignee));
        }
        StringBuilder sb = new StringBuilder();
        tasks = tasks.sorted(Comparator.comparing(Task::getTitle));
        tasks.forEach(task -> sb.append(task.getTitle()).append("\n"));

        return sb.toString();
    }
}
