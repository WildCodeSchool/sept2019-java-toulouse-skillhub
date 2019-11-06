package com.wildcodeschool.skillhub.entity;

import java.util.Date;

public class Question {

    private Long id_question;
    private String title;
    private String body;
    private Date date;
    private boolean resolved;
    private Long id_user;

    public Question() { }

    public Question(Long id_question, String title, String body, Date date, boolean resolved, Long id_user) {
        this.id_question = id_question;
        this.title = title;
        this.body = body;
        this.date = date;
        this.resolved = resolved;
        this.id_user = id_user;
    }

    public Long getId_question() {
        return id_question;
    }

    public void setId_question(Long id_question) {
        this.id_question = id_question;
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

    public Date getDate() {
        return date;
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

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
}
