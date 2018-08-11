package com.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

public class Jump implements Serializable{

    private Integer id;
    private String name;
    private String phone;
    private Integer age;
    private Date brithday;
    private String file;

    @Override
    public String toString() {
        return "Jump{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", brithday=" + brithday +
                ", file='" + file + '\'' +
                '}';
    }

    public Jump() {
    }

    public Jump(Integer id, String name, String phone, Integer age, Date brithday, String file) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.brithday = brithday;
        this.file = file;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
