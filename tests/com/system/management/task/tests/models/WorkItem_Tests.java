package com.system.management.task.tests.models;

import com.system.management.task.models.CommentImpl;
import com.system.management.task.models.StoryImpl;
import com.system.management.task.models.contracts.Comment;
import com.system.management.task.models.contracts.Story;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkItem_Tests {

    private Story s;

    @BeforeEach
    void init() {
        this.s = new StoryImpl(1, "storyTitle", "description", Status.DONE, Priority.HIGH, Size.MEDIUM);
    }


    @Test
    public void getId_Should_getId() {
        Assertions.assertEquals(1, s.getId());
    }
    @Test
    public void getTitle_Should_getTitle() {
        Assertions.assertEquals("storyTitle", s.getTitle());
    }
    @Test
    public void setTitle_Should_setTitle() {
        s.setTitle("NewStoryTitle");
        Assertions.assertEquals("NewStoryTitle", s.getTitle());
    }
    @Test
    public void getDescription_Should_getDescription() {
        Assertions.assertEquals("description", s.getDescription());
    }
    @Test
    public void setDescription_Should_setDescription() {
        s.setDescription("NewStoryDescription");
        Assertions.assertEquals("NewStoryDescription", s.getDescription());
    }
    @Test
    public void getStatus_Should_getStatus() {
        Assertions.assertEquals(Status.DONE.toString(), s.getStatus().toString());
    }
    @Test
    public void addComment_Should_AddComment() {
        s.addComment(new CommentImpl("author", "content"));
        Assertions.assertEquals(1, s.getComments().size());
        Assertions.assertEquals(1, s.getHistory().size());
    }
    @Test
    public void removeComment_Should_RemoveComment() {
        Comment c = new CommentImpl("author", "content");
        s.addComment(c);
        s.removeComment(c);
        Assertions.assertEquals(0, s.getComments().size());
        Assertions.assertEquals(2, s.getHistory().size());
    }
    @Test
    public void printComments_Should_PrintComments() {
        Comment c = new CommentImpl("author", "content");
        Comment c1 = new CommentImpl("author", "content");
        s.addComment(c);
        s.addComment(c1);
        String str = String.format("Task [storyTitle] Comments: \n[content] by author\n[content] by author\n");
        Assertions.assertEquals(str, s.printComments());
    }
    @Test
    public void getComments_Should_getComments() {
        Comment c = new CommentImpl("author", "content");
        s.addComment(c);
        Assertions.assertEquals(1, s.getComments().size());
    }
    @Test
    public void getHistory_Should_getHistory() {
        Comment c = new CommentImpl("author", "content");
        s.addComment(c);
        Assertions.assertEquals(1, s.getHistory().size());
    }
    @Test
    public void changeStatus_Should_ChangeStatus() {
        s.changeStatus(Status.NOT_DONE);
        Assertions.assertEquals(Status.NOT_DONE.toString(), s.getStatus().toString());
        Assertions.assertEquals(1, s.getHistory().size());
    }
}