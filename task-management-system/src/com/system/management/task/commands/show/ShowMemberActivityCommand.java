package com.system.management.task.commands.show;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

public class ShowMemberActivityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowMemberActivityCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberName = parameters.get(0);
        String activity = showMemberActivity(memberName);
        return String.format("Member: %s\nActivity history:\n%s", memberName, activity);
    }

    private String showMemberActivity(String memberName) {
        Member member = getRepoHelper().findMemberByName(memberName);
        return member.getActivityHistory();
    }
}
