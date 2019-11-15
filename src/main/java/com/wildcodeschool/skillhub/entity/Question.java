package com.wildcodeschool.skillhub.entity;
import java.util.Date;

public class Question {

    private Long userId;
    private Long questionId;
    private String title;
    private String body;
    private String bodyPreview;
    private Date date;
    private boolean resolved;
    private String author;
    private String authorAvatarUrl;
    private String skill;
    private Long nbAnswers = 0l;

    public Question(Long userId, Long questionId, String title, String body, Date date, boolean resolved, String author, String authorAvatarUrl, String skill) {
        this.userId = userId;
        this.questionId = questionId;
        this.title = title;
        this.body = body;
        this.bodyPreview = this.body.length() > 130 ? body.substring(0, 130) + "..." : body;
        this.date = date;
        this.resolved = resolved;
        this.author = author;
        this.authorAvatarUrl = authorAvatarUrl;
        this.skill = skill;
    }

    public Question(Long userId, Long questionId, String title, String body, Date date, boolean resolved) {
        this.userId = userId;
        this.questionId = questionId;
        this.title = title;
        this.body = body;
        this.date = date;
        this.resolved = resolved;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public String getBodyPreview() {
        return bodyPreview;
    }

    public void setBodyPreview(String bodyPreview) {
        this.bodyPreview = bodyPreview;
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

    public Long getNbAnswers() {
        return nbAnswers;
    }

    public void setNbAnswers(Long nbAnswers) {
        this.nbAnswers = nbAnswers;
    }
}