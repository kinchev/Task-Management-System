package com.system.management.task.models;

import com.system.management.task.models.contracts.Comment;

import static com.system.management.task.models.ModelConstants.EMPTY_CONTENT;

public class CommentImpl implements Comment {

    private final String author;
    private String content;

    public CommentImpl(String author, String content) {
        this.author = author;
        setContent(content);
    }

    @Override
    public String viewComment() {
        return String.format("[%s] by %s", this.content, this.author);
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    private void setContent(String content) {
        if (content.length() == 0) {
            throw new IllegalArgumentException(EMPTY_CONTENT);
        }
        this.content = content;
    }

    @Override
    public boolean equals(Object c) {
        if (c == this)
            return true;
        if (!(c instanceof Comment))
            return false;
        Comment other = (Comment) c;
        return this.content.equals(other.getContent())
                && this.author.equals(other.getAuthor());
    }
}
