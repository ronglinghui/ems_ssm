package com.baizhi.entity;

import java.io.Serializable;

public class Manager implements Serializable {
    private Integer id;//主键
    private  String username;//登录名
    private String name;//名字
    private String password;//密码
    private String sex;//性别
    private String status;//盐值
    public Manager() {
    }

    public Manager(Integer id, String username, String name, String password, String sex,String status) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

