package com.nowcoder.community.entity;

/*
封装分页相关的组件
 */

public class Page {

    //当前的页码
    private int current = 1;
    // 页面显示上限
    private int limit = 10;
    // 数据的总数用于计算总页数
    private int rows;
    // 查询路径 用来复用分页的连接
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1)
            this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1 && limit<=100)
            this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0)
            this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    // 偏移量当前页根据页码算出起始行
    public int getOffset(){
        // 根据当前页current*limit-limit
        return (current-1)*limit;
    }
    public int getTotal(){
        // 用来获取总的页数
        if(rows%limit==0)   return rows/limit;
        else return rows/limit + 1;
    }
    /*
    获取起始页码
     */
    public int getFrom(){
        int from = current-2;
        return from<1 ? 1: from;
    }

    public int getTo(){
        int to = current +2;
        int total = getTotal();
        return to>total ? total : to;
    }

}
