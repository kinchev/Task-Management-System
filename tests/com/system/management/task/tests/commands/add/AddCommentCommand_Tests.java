package com.system.management.task.tests.commands.add;

import com.system.management.task.commands.add.AddCommentCommand;
import com.system.management.task.commands.contracts.Command;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.models.CommentImpl;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;

public class AddCommentCommand_Tests {
    private RepositoryHelper repoHelper;
    private Command command;
    @BeforeEach
    void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createStory(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.NOT_DONE,Priority.HIGH, Size.MEDIUM);
        command =  new AddCommentCommand(repoHelper);
    }
    @Test
    public void addComment_Should_AddComment_When_ValidParameters() {
        command.execute(Arrays.asList( "content","1","author"));
        Assertions.assertTrue(repoHelper.findWorkItemById(1).getComments().contains(new CommentImpl("author", "content")));
    }
    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {

        Assertions.assertThrows(IllegalArgumentException.class,() -> command.execute(Arrays.asList( "content","1")));
    }
}
