package com.mdms.yiliao.controller;

import com.mdms.yiliao.entity.Device;
import com.mdms.yiliao.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceMapper deviceMapper;

    @GetMapping("/device")
    public List query(){

        List<Device> list = deviceMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    @PostMapping("/device")
    public String save(Device device){
        int i;
        // System.out.println(device);
        i = deviceMapper.insert(device);
        if(i > 0) {
            return "插入成功";
        }else{
            return "失败";
        }
    }

}
