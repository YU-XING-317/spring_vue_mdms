package com.mdms.yiliao.entity;

import com.baomidou.mybatisplus.annotation.TableId;

// 工程师类
public class Employer {
    // 工程师Id
    @TableId
    private String eid;
    // 工程师姓名
    private String ename;
    // 工程师职位（维保 or 检修）
    private String epos;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "eid='" + eid + '\'' +
                ", ename='" + ename + '\'' +
                ", epos='" + epos + '\'' +
                '}';
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEpos() {
        return epos;
    }

    public void setEpos(String epos) {
        this.epos = epos;
    }
}
