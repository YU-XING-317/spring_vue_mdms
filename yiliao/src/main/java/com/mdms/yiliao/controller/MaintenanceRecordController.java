package com.mdms.yiliao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdms.yiliao.entity.MaintenanceRecord;
import com.mdms.yiliao.mapper.MaintenanceRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MaintenanceRecordController {
    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;

    //添加维保记录
    @PostMapping("/mRecord")
    public String maintenanceRecordInsert(MaintenanceRecord maintenanceRecord){
        int i;
        System.out.println(maintenanceRecord);
        i = maintenanceRecordMapper.insert(maintenanceRecord);
        if(i > 0) {
            return "插入成功";
        }else{
            return "失败";
        }
    }

    //修改维保记录
    @PutMapping("/mRecord")
    public int maintenanceRecordUpdate(MaintenanceRecord maintenanceRecord){
        return maintenanceRecordMapper.updateById(maintenanceRecord);
    }

    //查询所有维保记录
    @GetMapping("/mRecord")
    public List maintenanceRecordList() {
        List<MaintenanceRecord> list = maintenanceRecordMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    //按设备id查询维保记录
    @GetMapping("/mRecord/did")
    public List maintenanceRecordListByDname(String Did){
        QueryWrapper<MaintenanceRecord> queryWrapper = new QueryWrapper();
        queryWrapper.eq("did",Did);
        List<MaintenanceRecord> list = maintenanceRecordMapper.selectList(queryWrapper);
        return list;
    }
}
