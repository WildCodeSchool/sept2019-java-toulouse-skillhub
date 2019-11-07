package com.wildcodeschool.skillhub.entity;

import java.util.Date;

public class User {

    private Long idUser;
    private String nickname;
    private String password;
    private Long idPicture;
    private String url;

    public User (){}

    public User(Long idUser, String nickname, String password, Long idPicture, String url) {
        this.idUser = idUser;
        this.nickname = nickname;
        this.password = password;
        this.idPicture = idPicture;
        this.url = url;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(Long idPicture) {
        this.idPicture = idPicture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
