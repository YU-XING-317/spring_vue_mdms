package com.mdms.yiliao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.util.List;

// 维保记录类
public class MaintenanceRecord {
    // 维保记录Id
    @TableId
    private String mrid;
    // 维保时间
    private Date mrtime;
    // 维保内容
    private String mrcontent;
    // 维保
    private String mrentity;
    // 维保状态
    private String mrstate;
    // 维保记录
    private String mrexp;
    // 维保备注
    private String mrmark;
    // 维保设备Id
    private String did;
    // 维保工程师Id
    private String eid;

    @TableField(exist = false)
    private List<Device> devices;

    @Override
    public String toString() {
        return "MaintenanceRecord{" +
                "mrid='" + mrid + '\'' +
                ", mrtime=" + mrtime +
                ", mrcontent='" + mrcontent + '\'' +
                ", mrentity='" + mrentity + '\'' +
                ", mrstate='" + mrstate + '\'' +
                ", mrexp='" + mrexp + '\'' +
                ", mrmark='" + mrmark + '\'' +
                ", did='" + did + '\'' +
                ", eid='" + eid + '\'' +
                '}';
    }

    public String getMrid() {
        return mrid;
    }

    public void setMrid(String mrid) {
        this.mrid = mrid;
    }

    public Date getMrtime() {
        return mrtime;
    }

    public void setMrtime(Date mrtime) {
        this.mrtime = mrtime;
    }

    public String getMrcontent() {
        return mrcontent;
    }

    public void setMrcontent(String mrcontent) {
        this.mrcontent = mrcontent;
    }

    public String getMrentity() {
        return mrentity;
    }

    public void setMrentity(String mrentity) {
        this.mrentity = mrentity;
    }

    public String getMrstate() {
        return mrstate;
    }

    public void setMrstate(String mrstate) {
        this.mrstate = mrstate;
    }

    public String getMrexp() {
        return mrexp;
    }

    public void setMrexp(String mrexp) {
        this.mrexp = mrexp;
    }

    public String getMrmark() {
        return mrmark;
    }

    public void setMrmark(String mrmark) {
        this.mrmark = mrmark;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
