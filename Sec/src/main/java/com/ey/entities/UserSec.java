package com.ey.entities;

public class UserSec {

    private String id;
    private String group_id;
    private String group_provider;
    private String user_id;
    private String user_provider;

    public UserSec() {
    }

    public UserSec(String id, String group_id, String group_provider, String user_id, String user_provider) {
        this.id = id;
        this.group_id = group_id;
        this.group_provider = group_provider;
        this.user_id = user_id;
        this.user_provider = user_provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_provider() {
        return group_provider;
    }

    public void setGroup_provider(String group_provider) {
        this.group_provider = group_provider;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_provider() {
        return user_provider;
    }

    public void setUser_provider(String user_provider) {
        this.user_provider = user_provider;
    }

    @Override
    public String toString() {
        return "UserSec{" +
                "id='" + id + '\'' +
                ", group_id='" + group_id + '\'' +
                ", group_provider='" + group_provider + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_provider='" + user_provider + '\'' +
                '}';
    }
}
