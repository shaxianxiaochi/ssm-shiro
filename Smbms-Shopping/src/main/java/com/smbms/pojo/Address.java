package com.smbms.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc: 
 * @Author: noking
 */
public class Address implements Serializable {

    private Integer id;
    private String contact;
    private String addressDesc;
    private String postCode;
    private String tel;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private Integer userId;

    public Address() {
    }

    public Address(Integer id, String contact, String addressDesc, String postCode, String tel, Integer createdBy, Date creationDate, Integer modifyBy, Date modifyDate, Integer userId) {
        this.id=id;
        this.contact=contact;
        this.addressDesc=addressDesc;
        this.postCode=postCode;
        this.tel=tel;
        this.createdBy=createdBy;
        this.creationDate=creationDate;
        this.modifyBy=modifyBy;
        this.modifyDate=modifyDate;
        this.userId=userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                "contact='" + contact + '\'' +
                "addressDesc='" + addressDesc + '\'' +
                "postCode='" + postCode + '\'' +
                "tel='" + tel + '\'' +
                "createdBy='" + createdBy + '\'' +
                "creationDate='" + creationDate + '\'' +
                "modifyBy='" + modifyBy + '\'' +
                "modifyDate='" + modifyDate + '\'' +
                "userId='" + userId + '\'' +
                '}';
    }
}
