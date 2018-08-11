package com.faq.pojo;

import java.util.Date;

public class Faqs {

    private Integer id;
    private String title;
    private Date createDate;
    private String content;
    private Integer classid;

    private String cName;


    @Override
    public String toString() {
        return "Faqs{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createDate=" + createDate +
                ", content='" + content + '\'' +
                ", classid=" + classid +
                ", cName='" + cName + '\'' +
                '}';
    }

    public Faqs() {
    }

    public Faqs(Integer id, String title, Date createDate, String content, Integer classid, String cName) {

        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.content = content;
        this.classid = classid;
        this.cName = cName;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
