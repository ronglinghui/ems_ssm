package com.baizhi.entity;

/**
 * 分页的实体类
 * */
public class Sybase {
    /**
     *  定义分页的属性
     * */
    private int pageNumber;//页码
    private int pageCountNumbet;//总页码
    private int countNumber;//总条数
    private int count;//每页显示几条

    //计算总页数
    public int tatolPages(){
        //判断是否是整页
        if (this.countNumber%this.count==0){
            return this.countNumber/this.count;
        }else {
            return this.countNumber%this.count;
        }
    }
    //计算开始条数
    public int  getInitially(){
        //计算
        return 1+this.count*(this.pageNumber-1);
    }

    public Sybase() {
        //创建对象设计给每页赋值
        this.count = 5;
    }

    public Sybase(int pageNumber, int pageCountNumbet, int countNumber, int count) {
        this.pageNumber = pageNumber;
        this.pageCountNumbet = pageCountNumbet;
        this.countNumber = countNumber;
        this.count = count;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageCountNumbet() {
        return pageCountNumbet;
    }

    /*public void setPageCountNumbet(int pageCountNumbet) {
        this.pageCountNumbet = pageCountNumbet;
    }*/

    public int getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(int countNumber) {
        this.countNumber = countNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
