package com.mdms.yiliao.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

// 维保记录类
public class MaintenanceRecord {
    // 维保记录Id
    @TableId
    private String mrId;
    // 维保时间
    private Date mrTime;
    // 维保内容
    private String mrContent;
    // 维保
    private String mrEntity;
    // 维保状态
    private String mrState;
    // 维保记录
    private String mrExp;
    // 维保备注
    private String mrMark;
    // 维保设备Id
    private String did;
    // 维保工程师Id
    private String eid;

    public String getMrId() {
        return mrId;
    }

    public void setMrId(String mrId) {
        this.mrId = mrId;
    }

    public Date getMrTime() {
        return mrTime;
    }

    public void setMrTime(Date mrTime) {
        this.mrTime = mrTime;
    }

    public String getMrContent() {
        return mrContent;
    }

    public void setMrContent(String mrContent) {
        this.mrContent = mrContent;
    }

    public String getMrEntity() {
        return mrEntity;
    }

    public void setMrEntity(String mrEntity) {
        this.mrEntity = mrEntity;
    }

    public String getMrState() {
        return mrState;
    }

    public void setMrState(String mrState) {
        this.mrState = mrState;
    }

    public String getMrExp() {
        return mrExp;
    }

    public void setMrExp(String mrExp) {
        this.mrExp = mrExp;
    }

    public String getMrMark() {
        return mrMark;
    }

    public void setMrMark(String mrMark) {
        this.mrMark = mrMark;
    }

    public String getDid() { return did; }

    public void setDid(String did) { this.did = did; }

    public String getEid() { return eid; }

    public void setEid(String eid) { this.eid = eid; }

    @Override
    public String toString() {
        return "MaintenanceRecord{" +
                "mrId='" + mrId + '\'' +
                ", mrTime=" + mrTime +
                ", mrContent='" + mrContent + '\'' +
                ", mrEntity='" + mrEntity + '\'' +
                ", mrState='" + mrState + '\'' +
                ", mrExp='" + mrExp + '\'' +
                ", mrMark='" + mrMark + '\'' +
                ", did='" + did + '\'' +
                ", eid='" + eid + '\'' +
                '}';
    }
}
