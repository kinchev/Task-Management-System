package com.system.management.task.commands.remove;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.REMOVE_MEMBER;

public class RemoveMemberCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public RemoveMemberCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String memberName = parameters.get(1);
        Member m = getRepoHelper().findMemberByName(memberName);
        getRepoHelper().findTeamByName(teamName).getMembers().remove(m);
        return String.format(REMOVE_MEMBER, memberName, teamName);
    }
}
