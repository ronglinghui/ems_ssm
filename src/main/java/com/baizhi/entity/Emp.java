package com.baizhi.entity;

import java.io.Serializable;

public class Emp implements Serializable {
    private Integer id;//主键
    private String name;//名字
    private Double salary;//工资
    private Integer age;//年龄
    private String birthday;//生日
    private String sex;//性别

    public Emp() {
    }

    public Emp(Integer id, String name, Double salary, Integer age, String birthday, String sex) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.birthday = birthday;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
