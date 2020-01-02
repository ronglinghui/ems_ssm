package com.baizhi.service;

import com.baizhi.entity.Manager;

public interface ManagerService {
    //登录
    public Manager login(String username);
    //注册
    public void register(Manager manager);
}
