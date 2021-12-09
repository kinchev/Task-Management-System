package com.system.management.task.tests.models;

import com.system.management.task.models.CommentImpl;
import com.system.management.task.models.contracts.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentImpl_Tests {

    private Comment comment;

    @BeforeEach
    void init() {
        this.comment = new CommentImpl("author", "comment");
    }

    @Test
    public void CommentImpl_ShouldImplementTaskInterface() {
        Comment comment = new CommentImpl("Author", "content");
        Assertions.assertTrue(comment instanceof Comment);
    }

    @Test
    public void constructor_should_throwException_when_ContentIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CommentImpl("Author", ""));
    }

    @Test
    public void constructor_should_createComment_whenArgumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> new CommentImpl("auhor", "comment"));
    }

    @Test
    public void getAuthor_should_getAuthor() {
        Assertions.assertEquals("author", comment.getAuthor());
    }

    @Test
    public void getContent_should_getContent() {
        Assertions.assertEquals("comment", comment.getContent());
    }

    @Test
    public void viewComment_should_viewcomment() {
        String s = "[comment] by author";
        Assertions.assertEquals(s, comment.viewComment());
    }
    @Test
    public void equals_should_returnTrue_WhenObjectsHaveEqualValues() {
        Comment comment = new CommentImpl("Author", "content");
        Comment comment2 = new CommentImpl("Author", "content");
        Assertions.assertTrue(comment instanceof Comment);
        Assertions.assertTrue(comment.equals(comment2));
        }
}