package com.mdms.yiliao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdms.yiliao.entity.MaintenanceRecord;
import com.mdms.yiliao.mapper.DeviceMapper;
import com.mdms.yiliao.mapper.MaintenanceRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaintenanceRecordController {
    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    //添加维保记录
    @PostMapping("/mRecord")
    public String maintenanceRecordInsert(@RequestBody MaintenanceRecord maintenanceRecord){
        int i;
        i = maintenanceRecordMapper.selectCount(null);
        maintenanceRecord.setMrid(Integer.toString(i+1));
        System.out.println(maintenanceRecord);
        i = maintenanceRecordMapper.insert(maintenanceRecord);
        if(i > 0) {
            return "插入成功";
        }else{
            return "失败";
        }
    }

    //修改维保记录
    @PutMapping("/mRecord/mrid")
    public int maintenanceRecordUpdate(@RequestBody MaintenanceRecord maintenanceRecord){
        System.out.println("调用修改维保记录功能");
        System.out.println(maintenanceRecord);
        return maintenanceRecordMapper.updateById(maintenanceRecord);
    }

    //查询维保记录
    @GetMapping("/mRecord")
    public List maintenanceRecordList(@RequestParam("did") String did) {
        System.out.println("调用查询维保记录功能");
        System.out.println("所查记录，设备Id为：" + did);
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setDid(did.isEmpty() ? null : did);
        QueryWrapper<MaintenanceRecord> queryWrapper = new QueryWrapper(maintenanceRecord);
        List<MaintenanceRecord> list = maintenanceRecordMapper.selectList(queryWrapper);
        System.out.println(list);
        return list;
    }

//    //按设备id查询维保记录
//    @GetMapping("/mRecord/did")
//    public List maintenanceRecordListByDname(String Did){
//        QueryWrapper<MaintenanceRecord> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("did",Did);
//        List<MaintenanceRecord> list = maintenanceRecordMapper.selectList(queryWrapper);
//        return list;
//    }
}
