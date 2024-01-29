package com.mdms.yiliao.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

// 检修记录
public class RepairRecord {
    // 检修记录Id
    @TableId
    private String rrId;
    // 检修时间
    private Date rrTime;
    // 检修内容
    private String rrContent;
    // 检修状态
    private String rrState;
    // 检修结果
    private String rrResult;
    // 检修备注
    private String rrMark;

    public String getRrId() {
        return rrId;
    }

    public void setRrId(String rrId) {
        this.rrId = rrId;
    }

    public Date getRrTime() {
        return rrTime;
    }

    public void setRrTime(Date rrTime) {
        this.rrTime = rrTime;
    }

    public String getRrContent() {
        return rrContent;
    }

    public void setRrContent(String rrContent) {
        this.rrContent = rrContent;
    }

    public String getRrState() {
        return rrState;
    }

    public void setRrState(String rrState) {
        this.rrState = rrState;
    }

    public String getRrResult() {
        return rrResult;
    }

    public void setRrResult(String rrResult) {
        this.rrResult = rrResult;
    }

    public String getRrMark() {
        return rrMark;
    }

    public void setRrMark(String rrMark) {
        this.rrMark = rrMark;
    }

    @Override
    public String toString() {
        return "RepairRecord{" +
                "rrId='" + rrId + '\'' +
                ", rrTime=" + rrTime +
                ", rrContent='" + rrContent + '\'' +
                ", rrState='" + rrState + '\'' +
                ", rrResult='" + rrResult + '\'' +
                ", rrMark='" + rrMark + '\'' +
                '}';
    }
}
