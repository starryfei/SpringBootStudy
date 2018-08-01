package com.starry.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "account_id")
    private String account;
    @Column(name = "phone_number")
    private String phone;
    @Column(name = "nick_name")
    private String nickName;
    private String pwd;

    protected Account() {

    }

    public Account(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", account=" + account + ", phone="
                + phone + ", nickName=" + nickName + ", pwd=" + pwd + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
