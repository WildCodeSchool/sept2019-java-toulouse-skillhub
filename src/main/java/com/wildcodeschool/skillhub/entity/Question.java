package com.wildcodeschool.skillhub.entity;

import java.util.Date;

public class Question {

    private Long id;
    private String title;
    private String body;
    private Date date;
    private boolean resolved;
    private String author;
    private String authorAvatarUrl;
    private String skill;

    public Question(Long id, String title, String body, Date date, boolean resolved, String author, String authorAvatarUrl, String skill) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.resolved = resolved;
        this.author = author;
        this.authorAvatarUrl = authorAvatarUrl;
        this.skill = skill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return String.format("%s/%s/%s",
                this.date.getDate(),
                this.date.getMonth(),
                this.date.getYear() + 1900);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorAvatarUrl() {
        return authorAvatarUrl;
    }

    public void setAuthorAvatarUrl(String authorAvatarUrl) {
        this.authorAvatarUrl = authorAvatarUrl;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
