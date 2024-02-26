package com.featurevotes.domain;

import jakarta.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    private CommentId pk;
    private String text;

    @EmbeddedId
    public CommentId getPk() {
        return pk;
    }

    public void setPk(CommentId pk) {
        this.pk = pk;
    }

    @Column(length=30000)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
