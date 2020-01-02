package com.baizhi.service.impl;

import com.baizhi.dao.EmpDao;
import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
    @Service("empService")//注解创建对象
    @Transactional//事物管理注解
public class EmpServiceImpl implements EmpService {
    @Autowired//自动注入
    private EmpDao empDao;
    @Override
    public void insertEmp(Emp emp) {
        empDao.insertEmp(emp);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)//查询的事物方式
    public List<Emp> selectEmp() {
        List<Emp> emps = empDao.selectEmp();
        return emps;
    }

    @Override
    public void deleteEmp(Integer id) {
        empDao.deleteEmp(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        empDao.updateEmp(emp);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)//查询的事物方式
    public List<Emp> selectByName(String name) {
        System.out.println("serblle========="+name);
        String s = "%"+name+"%";
            return empDao.selectByName(s);
        }
    }
