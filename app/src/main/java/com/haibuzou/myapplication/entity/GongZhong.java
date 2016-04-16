package com.haibuzou.myapplication.entity;

import java.util.List;

/**
 * Created by AAA on 2015/6/16.
 */
public class GongZhong extends BaseData {

    public GongZhong(int id, String name) {
        super(id, name);
    }

    private List<BaseData> jobtype;

    public List<BaseData> getJobtype() {
        return jobtype;
    }

    public void setJobtype(List<BaseData> jobtype) {
        this.jobtype = jobtype;
    }

}
