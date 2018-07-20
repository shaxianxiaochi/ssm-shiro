package com.shiro.pojo;

import java.io.Serializable;

public class Role implements Serializable{

    private int id;

    private String role;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public Role() {
    }

    public Role(int id, String role) {

        this.id = id;
        this.role = role;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
