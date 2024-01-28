package com.mdms.yiliao.controller;

import com.mdms.yiliao.mapper.RepairRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepairRecordController {
    @Autowired
    private RepairRecordMapper repairRecordMapper;

}
