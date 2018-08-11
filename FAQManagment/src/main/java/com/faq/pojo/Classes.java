package com.faq.pojo;

import java.util.Date;

public class Classes {
    private Integer id;
    private String cName;

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", cName='" + cName + '\'' +
                '}';
    }

    public Classes() {
    }

    public Classes(Integer id, String cName) {

        this.id = id;
        this.cName = cName;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
