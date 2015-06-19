package com.haibuzou.myapplication.entity;

/**
 * Created by AAA on 2015/6/16.
 */
public class BaseData {

    private int dataId;
    private String name;

    public BaseData() {

    }

    public BaseData(int id, String name) {
        this.dataId = id;
        this.name = name;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
