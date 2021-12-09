package com.system.management.task.core;

import com.system.management.task.commands.add.AddCommentCommand;
import com.system.management.task.commands.add.AddStepCommand;
import com.system.management.task.commands.add.AssignTaskCommand;
import com.system.management.task.commands.changes.*;
import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.creation.*;
import com.system.management.task.commands.enums.CommandType;
import com.system.management.task.commands.remove.RemoveCommentCommand;
import com.system.management.task.commands.remove.RemoveMemberCommand;
import com.system.management.task.commands.remove.RemoveStepCommand;
import com.system.management.task.commands.show.*;
import com.system.management.task.core.contracts.CommandFactory;
import com.system.management.task.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, RepositoryHelper repoHelper) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case CHANGEBUGSEVERITY:
                return new ChangeBugSeverityCommand(repoHelper);
            case CHANGEBUGPRIORITY:
                return new ChangeBugPriorityCommand(repoHelper);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivityCommand(repoHelper);
            case CREATETEAM:
                return new CreateTeamCommand(repoHelper);
            case SHOWMEMBERACTIVITY:
                return new ShowMemberActivityCommand(repoHelper);
            case SHOWMEMBERS:
                return new ShowMembersCommand(repoHelper);
            case SHOWALLTEAMSBOARDS:
                return new ShowAllTeamBoardsCommand(repoHelper);
            case SHOWTEAMMEMBERS:
                return new ShowTeamMembersCommand(repoHelper);
            case CREATEMEMBER:
                return new CreateMemberCommand(repoHelper);
            case REMOVEMEMBER:
                return new RemoveMemberCommand(repoHelper);
            case ADDCOMMENT:
                return new AddCommentCommand(repoHelper);
            case CREATEBUG:
                return new CreateBugCommand(repoHelper);
            case CREATESTORY:
                return new CreateStoryCommand(repoHelper);
            case CREATEFEEDBACK:
                return new CreateFeedbackCommand(repoHelper);
            case CREATEBOARD:
                return new CreateBoardCommand(repoHelper);
            case ADDSTEP:
                return new AddStepCommand(repoHelper);
            case SHOWSTEPS:
                return new ShowStepsCommand(repoHelper);
            case CHANGESTORYSIZE:
                return new ChangeStorySizeCommand(repoHelper);
            case CHANGESTORYPRIORITY:
                return new ChangeStoryPriorityCommand(repoHelper);
            case CHANGESTATUS:
                return new ChangeStatusCommand(repoHelper);
            case ASSIGNTASK:
                return new AssignTaskCommand(repoHelper);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivityCommand(repoHelper);
            case REMOVECOMMENT:
                return new RemoveCommentCommand(repoHelper);
            case PRINTCOMMENTS:
                return new PrintWorkItemComments(repoHelper);
            case REMOVESTEP:
                return new RemoveStepCommand(repoHelper);
            case CHANGERATING:
                return new ChangeRatingCommand(repoHelper);
            case SHOWWORKITEMACTIVITY:
                return new ShowWorkItemActivity(repoHelper);
            case SHOWTASKS:
                return new ShowTasksCommand(repoHelper);
            case SHOWASSIGNABLES:
                return new ShowAssignablesCommand(repoHelper);
            default:
                throw new IllegalArgumentException();
        }
    }
}
