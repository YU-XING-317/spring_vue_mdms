package com.mdms.yiliao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdms.yiliao.entity.Device;
import com.mdms.yiliao.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceMapper deviceMapper;

    //插入设备
    @PostMapping("/device")
    public String deviceInsert(@RequestBody Device device){
        System.out.println("添加设备信息功能");
        System.out.println(device);
        int i;
        i = deviceMapper.selectCount(null);
        String j = Integer.toString((i+1));
        device.setDid(j);
        System.out.println(device);
        // System.out.println(device);
        i = deviceMapper.insert(device);
        if(i > 0) {
            return "插入成功";
        }else{
            return "失败";
        }
    }

    //修改设备信息
    @PutMapping("/device")
    public int deviceUpdate(@RequestBody Device device){
        System.out.println("调用修改设备信息功能");
        System.out.println(device);
        return deviceMapper.updateById(device);
    }

    //查询设备信息
    @GetMapping("/device")
    public List devicesList(@RequestParam("dname") String name,
                            @RequestParam("dtype") String type,
                            @RequestParam("supplier") String sup){
        System.out.println("调用查询设备");
        Device device = new Device();
        device.setDname(name.isEmpty() ? null : name);
        device.setDtype(type.isEmpty() ? null : type);
        device.setSupplier(sup.isEmpty() ? null : sup);
        System.out.println("查询条件如下：");
        System.out.println(device);
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>(device);
        //queryWrapper.allEq({ dname:device.getDname(), dtype:device.getDtype(), supplier:device.getSupplier() },false);
        List<Device> list = deviceMapper.selectList(queryWrapper);
        System.out.println(list);
        return list;
    }

//    @GetMapping("/device/con")
//    public List devicesConditionalList(Device device){
//        QueryWrapper<Device> queryWrapper = new QueryWrapper();
//
//        List<Device> list = deviceMapper.selectList(null);
//        return list;
//    }
}
