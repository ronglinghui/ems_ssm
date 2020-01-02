package com.baizhi.service.impl;

import com.baizhi.dao.ManagerDao;
import com.baizhi.entity.Manager;
import com.baizhi.service.ManagerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service("managerService")//注解创建对象
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)//事物管理
    public Manager login(String username) {
        //调用方法
        Manager login = managerDao.login(username);
        return login;
    }

    @Override
    public void register(Manager manager) {
        manager.setStatus(UUID.randomUUID().toString());
        //密码经由MD5加密
        String s = DigestUtils.md5Hex(manager.getPassword() + manager.getStatus());
        manager.setPassword(s);
        //调用方法
        managerDao.register(manager);
    }
}
