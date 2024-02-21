package com.mdms.yiliao.entity;

import com.baomidou.mybatisplus.annotation.TableId;

// 医疗设备类
public class Device {
    // 设备Id
    @TableId
    private String did;
    // 设备名称
    private String dname;
    // 设备类型
    private String dtype;
    // 设备供应商
    private String supplier;
    // 设备所属医院
    private String hospital;
    // 设备所属科室
    private String department;
    //设备维保状态
    private String deviceState;
    //是否超期未保
    private String isOverdue;
    //检验状态
    private String isChecked;
    //检验结果
    private String checkResult;
    //设备风险状态
    private String isRisk;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getIsRisk() {
        return isRisk;
    }

    public void setIsRisk(String isRisk) {
        this.isRisk = isRisk;
    }

    @Override
    public String toString() {
        return "Device{" +
                "did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                ", dtype='" + dtype + '\'' +
                ", supplier='" + supplier + '\'' +
                ", hospital='" + hospital + '\'' +
                ", department='" + department + '\'' +
                ", deviceState='" + deviceState + '\'' +
                ", isOverdue='" + isOverdue + '\'' +
                ", isChecked='" + isChecked + '\'' +
                ", checkResult='" + checkResult + '\'' +
                ", isRisk='" + isRisk + '\'' +
                '}';
    }
}
