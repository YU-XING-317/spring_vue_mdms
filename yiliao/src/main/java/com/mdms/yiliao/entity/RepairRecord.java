package com.mdms.yiliao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.util.List;

// 检修记录
public class RepairRecord {
    // 检修记录Id
    @TableId
    private String rrid;
    // 检修时间
    private Date rrtime;
    // 检修内容
    private String rrcontent;
    // 检修状态
    private String rrstate;
    // 检修结果
    private String rrresult;
    // 检修备注
    private String rrmark;
    // 设备id
    private String did;
    // 工程师id
    private String eid;

    @TableField(exist = false)
    private List<Device> devices;

    public String getRrid() {
        return rrid;
    }

    public void setRrid(String rrid) {
        this.rrid = rrid;
    }

    public Date getRrtime() {
        return rrtime;
    }

    public void setRrtime(Date rrtime) {
        this.rrtime = rrtime;
    }

    public String getRrcontent() {
        return rrcontent;
    }

    public void setRrcontent(String rrcontent) {
        this.rrcontent = rrcontent;
    }

    public String getRrstate() {
        return rrstate;
    }

    public void setRrstate(String rrstate) {
        this.rrstate = rrstate;
    }

    public String getRrresult() {
        return rrresult;
    }

    public void setRrresult(String rrresult) {
        this.rrresult = rrresult;
    }

    public String getRrmark() {
        return rrmark;
    }

    public void setRrmark(String rrmark) {
        this.rrmark = rrmark;
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

    @Override
    public String toString() {
        return "RepairRecord{" +
                "rrid='" + rrid + '\'' +
                ", rrtime=" + rrtime +
                ", rrcontent='" + rrcontent + '\'' +
                ", rrstate='" + rrstate + '\'' +
                ", rrresult='" + rrresult + '\'' +
                ", rrmark='" + rrmark + '\'' +
                ", did='" + did + '\'' +
                ", eid='" + eid + '\'' +
                '}';
    }
}
