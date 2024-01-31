package com.mdms.yiliao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdms.yiliao.entity.RepairRecord;
import com.mdms.yiliao.mapper.RepairRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RepairRecordController {
    @Autowired
    private RepairRecordMapper repairRecordMapper;

    //添加检修记录
    @PostMapping("/rRecord")
    public String repairRecordInsert(@RequestBody RepairRecord repairRecord){
        int i;
        i = repairRecordMapper.selectCount(null) + 1;
        repairRecord.setRrid(Integer.toString(i));
        System.out.println(repairRecord);
        i = repairRecordMapper.insert(repairRecord);
        if(i > 0) {
            return "插入成功";
        }else{
            return "失败";
        }
    }

    //修改检修记录
    @PutMapping("/rRecord")
    public int repairRecordUpdate(RepairRecord repairRecord){
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

//    //按设备id查询维保记录
//    @GetMapping("/rRecord/did")
//    public List repairRecordListByDname(String Did){
//        QueryWrapper<RepairRecord> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("did",Did);
//        List<RepairRecord> list = repairRecordMapper.selectList(queryWrapper);
//        return list;
//    }

}
