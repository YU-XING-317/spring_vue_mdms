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

    //添加维保记录
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

    //修改维保记录
    @PutMapping("/rRecord")
    public int repairRecordUpdate(RepairRecord repairRecord){
        return repairRecordMapper.updateById(repairRecord);
    }

    //查询所有维保记录
    @GetMapping("/rRecord")
    public List repairRecordList() {
        List<RepairRecord> list = repairRecordMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    //按设备id查询维保记录
    @GetMapping("/rRecord/did")
    public List repairRecordListByDname(String Did){
        QueryWrapper<RepairRecord> queryWrapper = new QueryWrapper();
        queryWrapper.eq("did",Did);
        List<RepairRecord> list = repairRecordMapper.selectList(queryWrapper);
        return list;
    }

}
