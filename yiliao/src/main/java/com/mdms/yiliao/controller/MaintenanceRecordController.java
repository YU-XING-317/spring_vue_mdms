package com.mdms.yiliao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdms.yiliao.entity.DeviceFeature;
import com.mdms.yiliao.entity.MaintenanceRecord;
import com.mdms.yiliao.mapper.DeviceMapper;
import com.mdms.yiliao.mapper.MaintenanceRecordMapper;
import com.mdms.yiliao.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MaintenanceRecordController {
    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;


    @Autowired
    private DeviceMapper deviceMapper;


    @Autowired
    private PredictionService predictionService;

    public Integer getPredictionForDevice(String deviceId) {

        DeviceFeature deviceFeature = deviceMapper.listDevice(deviceId);
        Map<String, Object> featuresMap = new HashMap<>();
        System.out.println("调用预测功能");
        System.out.println(deviceFeature);
        featuresMap.put("设备维保状态", deviceFeature.getDeviceState());
        featuresMap.put("是否超期未保", deviceFeature.getIsOverdue());
        featuresMap.put("检验状态", deviceFeature.getIsChecked());
        featuresMap.put("检验结果", deviceFeature.getCheckResult());
        Integer risk= predictionService.predict(featuresMap);
        deviceMapper.updateDeviceRisk(deviceFeature.getDid(), risk);


        return 0 ;
    }


    //添加维保记录
    @PostMapping("/mRecord")
    public String maintenanceRecordInsert(@RequestBody MaintenanceRecord maintenanceRecord){
        int i;
        System.out.println("调用添加维保记录功能");
        i = maintenanceRecordMapper.selectCount(null);
        maintenanceRecord.setMrid(Integer.toString(i+1));
        System.out.println(maintenanceRecord);
        i = maintenanceRecordMapper.insert(maintenanceRecord);

        if(i > 0) {
            //更新此id的deviceStatus和isOverdue
            Integer x=deviceMapper.updateDeviceState(maintenanceRecord.getDid(), maintenanceRecord.getMrstate(), maintenanceRecord.getMrexp());
            System.out.println("更新设备状态："+x);
            //预测设备风险
            System.out.println("预测设备风险");
            getPredictionForDevice(maintenanceRecord.getDid());
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
