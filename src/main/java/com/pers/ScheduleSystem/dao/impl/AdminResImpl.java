package com.pers.ScheduleSystem.dao.impl;

import com.pers.ScheduleSystem.dao.AdminRes;
import com.pers.ScheduleSystem.dao.Po.adminPo;
import com.pers.ScheduleSystem.dao.mapper.AdminInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @description: 管理员repository
 * @author: sunyujie
 * @time: 2023/3/27 12:04
 * @version: 1.0
 */
@Repository
public class AdminResImpl implements AdminRes {

    @Autowired
    AdminInfoMapper adminInfoMapper;

    @Override
    public adminPo getAdminByName(String name){
        return adminInfoMapper.getAdminByName(name);
    }

}
