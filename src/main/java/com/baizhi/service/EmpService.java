package com.baizhi.service;

import com.baizhi.entity.Emp;

import java.util.List;

public interface EmpService {
    //添加
    public void insertEmp(Emp emp);
    //查询所有
    public List<Emp> selectEmp();
    //删除
    public void  deleteEmp(Integer id);
    //修改
    public void updateEmp(Emp emp);
    //名字查询
    public List<Emp> selectByName(String name);
}
