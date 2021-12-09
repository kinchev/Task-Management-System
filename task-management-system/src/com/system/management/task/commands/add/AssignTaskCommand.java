package com.system.management.task.commands.add;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.models.contracts.Task;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.system.management.task.commands.CommandConstants.ASSIGN_TASK;

public class AssignTaskCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public AssignTaskCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), "Id must be a number");
        String memberName = parameters.get(1);
        assignTask(id, memberName);
        return String.format(ASSIGN_TASK, id, memberName);
    }

    private void assignTask(int id, String memberName) {
        List<Task> res = new ArrayList<>();
        res.addAll(getRepoHelper().getBugs());
        res.addAll(getRepoHelper().getStories());
        Task task = getRepoHelper().findElementById(id, res);

        Member member = getRepoHelper().findMemberByName(memberName);
        task.setAssignee(member);
        member.addTask(task);
    }
}
