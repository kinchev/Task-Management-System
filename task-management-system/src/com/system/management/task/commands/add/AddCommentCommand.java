package com.system.management.task.commands.add;

import com.system.management.task.commands.creation.BaseCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.models.CommentImpl;
import com.system.management.task.models.contracts.Comment;
import com.system.management.task.models.contracts.WorkItem;
import com.system.management.task.utils.ParsingHelpers;
import com.system.management.task.utils.ValidationHelpers;

import java.util.List;

import static com.system.management.task.commands.CommandConstants.ADD_COMMENT;
import static com.system.management.task.commands.CommandConstants.ID_MUST_BE_NUMBER;


public class AddCommentCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public AddCommentCommand(RepositoryHelper repoHelper) {
        super(repoHelper);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(1), ID_MUST_BE_NUMBER);
        String author = parameters.get(2);
        String content = parameters.get(0);
        addComment(taskId, author, content);
        return ADD_COMMENT;
    }

    public void addComment(int id, String author, String content) {
        WorkItem item = getRepoHelper().findWorkItemById(id);
        Comment comment = new CommentImpl(author, content);
        item.addComment(comment);
    }
}
