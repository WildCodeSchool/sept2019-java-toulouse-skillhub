package com.wildcodeschool.skillhub.entity;

import java.util.List;

public class User {

    private Long userId;
    private String nickname;
    private String password;
    private String avatarUrl;
    private List<Long> skillsId;

    public User(Long userId, String nickname, String password, String avatarUrl, List<Long> skillsId) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.skillsId = skillsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Long> getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(List<Long> skillsId) {
        this.skillsId = skillsId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
