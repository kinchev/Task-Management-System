package com.system.management.task.tests.core;

import com.system.management.task.commands.enums.CommandType;
import com.system.management.task.core.CommandFactoryImpl;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.core.contracts.CommandFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandFactory_Tests {

    private CommandFactory cmd;
    private RepositoryHelper repoHelper;
    String[] cmds = new String[]{
            CommandType.ADDCOMMENT.toString(),
            CommandType.CREATETEAM.toString(),
            CommandType.SHOWTEAMMEMBERS.toString(),
            CommandType.CREATEMEMBER.toString(),
            CommandType.REMOVEMEMBER.toString(),
            CommandType.SHOWALLTEAMSBOARDS.toString(),
            CommandType.CREATEBOARD.toString(),
            CommandType.ADDSTEP.toString(),
            CommandType.SHOWSTEPS.toString(),
            CommandType.SHOWMEMBERS.toString(),
            CommandType.SHOWMEMBERACTIVITY.toString(),
            CommandType.SHOWTEAMACTIVITY.toString(),
            CommandType.SHOWBOARDACTIVITY.toString(),
            CommandType.CHANGEBUGPRIORITY.toString(),
            CommandType.CHANGEBUGSEVERITY.toString(),
            CommandType.CREATESTORY.toString(),
            CommandType.CREATEBUG.toString(),
            CommandType.CREATEFEEDBACK.toString(),
            CommandType.CHANGESTORYPRIORITY.toString(),
            CommandType.CHANGESTORYSIZE.toString(),
            CommandType.CHANGESTATUS.toString(),
            CommandType.ASSIGNTASK.toString(),
            CommandType.PRINTCOMMENTS.toString(),
            CommandType.REMOVECOMMENT.toString(),
            CommandType.REMOVESTEP.toString(),
            CommandType.CHANGERATING.toString(),
            CommandType.SHOWWORKITEMACTIVITY.toString(),
            CommandType.SHOWTASKS.toString(),
            CommandType.SHOWASSIGNABLES.toString()
    };

    @BeforeEach
    void init() {
        this.repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        cmd = new CommandFactoryImpl();
    }

    @Test
    public void createCommandFromCommandName_should_createCommand() {
        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i < cmds.length; i++) {
                cmd.createCommandFromCommandName(cmds[i], repoHelper);
            }
        });
    }

    @Test
    public void createCommandFromCommandName_should_ThrowException_WhenInvalidInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cmd.createCommandFromCommandName("invalid", repoHelper);
        });
    }
}
