package com.haibuzou.myapplication.entity;

import com.haibuzou.myapplication.entity.BaseData;

import java.util.List;

/**
 * Created by AAA on 2015/6/16.
 */
public class GongZhong extends BaseData {

    public GongZhong(int id, String name) {
        super(id, name);
        // TODO Auto-generated constructor stub
    }

    private List<BaseData> jobtype;

    public List<BaseData> getJobtype() {
        return jobtype;
    }

    public void setJobtype(List<BaseData> jobtype) {
        this.jobtype = jobtype;
    }

}
