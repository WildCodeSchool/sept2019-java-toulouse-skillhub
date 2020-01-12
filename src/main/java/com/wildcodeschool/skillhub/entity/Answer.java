package com.wildcodeschool.skillhub.entity;

import java.util.Date;

public class Answer {

    private String url;
    private String nickname;
    private String body;
    private Date date;
    private Long idUser;
    private Long idQuestion;
    private Long answerId;

    public Answer(Long answerId, Long idQuestion, String body, Date date, Long idUser) {
        this.idQuestion = idQuestion;
        this.body = body;
        this.date = date;
        this.answerId = answerId;
        this.idUser = idUser;
    }

    public Answer(String url, String nickname, String body, Date date, Long idUser, Long idQuestion) {
        this.url = url;
        this.nickname = nickname;
        this.body = body;
        this.date = date;
        this.idUser = idUser;
        this.idQuestion = idQuestion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
                "" + this.date.getMonth() + 1,
                this.date.getYear() + 1900);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }
}
