package com.shiro.pojo;

import java.io.Serializable;

public class Permission implements Serializable{

    private int id;
    private String token;
    private String url;
    private String description;
    private int roleId;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    public Permission() {
    }

    public Permission(int id, String token, String url, String description, int roleId) {

        this.id = id;
        this.token = token;
        this.url = url;
        this.description = description;
        this.roleId = roleId;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
