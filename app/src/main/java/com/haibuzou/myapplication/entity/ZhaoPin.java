package com.haibuzou.myapplication.entity;

import com.haibuzou.myapplication.entity.BaseData;
import com.haibuzou.myapplication.entity.GongZhong;

import java.util.List;

/**
 * Created by Administrator on 2015/6/16.
 */
public class ZhaoPin extends BaseData {

    public ZhaoPin(int id, String name) {
        super(id, name);
        // TODO Auto-generated constructor stub
    }

    private List<GongZhong> jobtype;

    public List<GongZhong> getJobtype() {
        return jobtype;
    }

    public void setJobtype(List<GongZhong> jobtype) {
        this.jobtype = jobtype;
    }

}
