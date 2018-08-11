package com.bbs.pojo;

import java.io.Serializable;
import java.util.Date;

public class BbsDetail implements Serializable{
    private int id;
    private int sortId;
    private String title;
    private String detail;
    private String author;
    private Date createDate;
    private int replyCount;

    //扩展属性
    private String sname;

    public BbsDetail() {
    }

    public BbsDetail(int id, int sortId, String title, String detail, String author, Date createDate, int replyCount, String sname) {

        this.id = id;
        this.sortId = sortId;
        this.title = title;
        this.detail = detail;
        this.author = author;
        this.createDate = createDate;
        this.replyCount = replyCount;
        this.sname = sname;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
