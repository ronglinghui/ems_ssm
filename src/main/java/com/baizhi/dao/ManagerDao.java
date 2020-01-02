package com.baizhi.dao;

import com.baizhi.entity.Manager;

public interface ManagerDao {
    //登录
    public Manager login(String username);
    //注册
    public void register(Manager manager);
}
