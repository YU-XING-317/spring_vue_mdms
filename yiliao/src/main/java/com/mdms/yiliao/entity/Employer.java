package com.mdms.yiliao.entity;

// 工程师类
public class Employer {
    // 工程师Id
    private String eId;
    // 工程师姓名
    private String eName;
    // 工程师职位（维保 or 检修）
    private String ePos;

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getePos() {
        return ePos;
    }

    public void setePos(String ePos) {
        this.ePos = ePos;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "eId='" + eId + '\'' +
                ", eName='" + eName + '\'' +
                ", ePos='" + ePos + '\'' +
                '}';
    }
}
