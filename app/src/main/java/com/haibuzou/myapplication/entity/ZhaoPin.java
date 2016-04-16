package com.haibuzou.myapplication.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/6/16.
 */
public class ZhaoPin extends BaseData {

    public ZhaoPin(int id, String name) {
        super(id, name);
    }

    private List<GongZhong> jobtype;

    public List<GongZhong> getJobtype() {
        return jobtype;
    }

    public void setJobtype(List<GongZhong> jobtype) {
        this.jobtype = jobtype;
    }

}
