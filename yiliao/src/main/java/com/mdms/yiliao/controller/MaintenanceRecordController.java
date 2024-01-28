package com.mdms.yiliao.controller;

import com.mdms.yiliao.mapper.MaintenanceRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaintenanceRecordController {
    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;

}
