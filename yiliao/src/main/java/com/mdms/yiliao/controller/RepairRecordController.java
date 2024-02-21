package com.mdms.yiliao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdms.yiliao.entity.DeviceFeature;
import com.mdms.yiliao.entity.Did;
import com.mdms.yiliao.entity.RepairRecord;
import com.mdms.yiliao.mapper.DeviceMapper;
import com.mdms.yiliao.mapper.RepairRecordMapper;
import com.mdms.yiliao.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RepairRecordController {
    @Autowired
    private RepairRecordMapper repairRecordMapper;

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private DeviceMapper deviceMapper;

    public Integer getPredictionForDevice(String deviceId) {

        DeviceFeature deviceFeature = deviceMapper.listDevice(deviceId);
        Map<String, Object> featuresMap = new HashMap<>();
        featuresMap.put("设备维保状态", deviceFeature.getDeviceState());
        featuresMap.put("是否超期未保", deviceFeature.getIsOverdue());
        featuresMap.put("检验状态", deviceFeature.getIsChecked());
        featuresMap.put("检验结果", deviceFeature.getCheckResult());
        Integer risk= predictionService.predict(featuresMap);
        deviceMapper.updateDeviceRisk(deviceFeature.getDid(), risk);



        return 0 ;
    }
    //添加检修记录
    @PostMapping("/rRecord")
    public String repairRecordInsert(@RequestBody RepairRecord repairRecord){
        System.out.println("调用添加检修记录功能");
        int i;
        i = repairRecordMapper.selectCount(null) + 1;
        repairRecord.setRrid(Integer.toString(i));
        System.out.println(repairRecord);
        i = repairRecordMapper.insert(repairRecord);

        if(i > 0) {
            //更新此id的isChecked字段和checkResult字段
            String did= repairRecord.getDid();
            deviceMapper.updateDeviceCheck(did, repairRecord.getRrstate(), repairRecord.getRrresult());
            //预测设备风险
            getPredictionForDevice(repairRecord.getDid());
            return "插入成功";
        }else{
            return "失败";
        }
    }

    //修改检修记录
    @PutMapping("/repairRecord")
    public int repairRecordUpdate(@RequestBody RepairRecord repairRecord){
        System.out.println("调用修改检修记录功能");
        return repairRecordMapper.updateById(repairRecord);
    }

    //查询检修记录
    @GetMapping("/rRecord")
    public List repairRecordList(@RequestParam("did") String did) {
        System.out.println("调用查询检修记录功能");
        System.out.println("所查记录，设备Id为：" + did);
        RepairRecord repairRecord = new RepairRecord();
        repairRecord.setDid(did.isEmpty() ? null : did);
        QueryWrapper<RepairRecord> queryWrapper = new QueryWrapper(repairRecord);
        List<RepairRecord> list = repairRecordMapper.selectList(queryWrapper);
        System.out.println(list);
        return list;
    }

    //获取设备id列表
    @GetMapping("/rRecord/id")
    public List devicesIdList(){
        System.out.println("调用查询设备id");
        List<Did> list = deviceMapper.listAllDeviceId();
        System.out.println(list);
        return list;
    }

//    //按设备id查询维保记录
//    @GetMapping("/rRecord/did")
//    public List repairRecordListByDname(String Did){
//        QueryWrapper<RepairRecord> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("did",Did);
//        List<RepairRecord> list = repairRecordMapper.selectList(queryWrapper);
//        return list;
//    }

}
