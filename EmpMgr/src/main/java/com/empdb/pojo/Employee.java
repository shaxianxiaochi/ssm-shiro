package com.empdb.pojo;

import java.util.Date;

/**
 * 员工类
 */
public class Employee {
    private Integer eId;
    private String eName;
    private Date eBirthday;
    private String eAddress;
    private String eSex;
    private Integer age;

    public Employee() {
    }

    public Employee(Integer eId, String eName, String eAddress, String eSex, Integer age) {
        this.eId = eId;
        this.eName = eName;
        this.eAddress = eAddress;
        this.eSex = eSex;
        this.age = age;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public Date geteBirthday() {
        return eBirthday;
    }

    public void seteBirthday(Date eBirthday) {
        this.eBirthday = eBirthday;
    }

    public String geteAddress() {
        return eAddress;
    }

    public void seteAddress(String eAddress) {
        this.eAddress = eAddress;
    }

    public String geteSex() {
        return eSex;
    }

    public void seteSex(String eSex) {
        this.eSex = eSex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
