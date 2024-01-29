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

    @Override
    public String toString() {
        return "Device{" +
                "did='" + did + '\'' +
                ", dname='" + dname + '\'' +
                ", dtype='" + dtype + '\'' +
                ", supplier='" + supplier + '\'' +
                ", hospital='" + hospital + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
