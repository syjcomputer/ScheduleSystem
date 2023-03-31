package com.pers.ScheduleSystem.dao.impl;

import com.pers.ScheduleSystem.dao.Po.SchedulePo;
import com.pers.ScheduleSystem.dao.ScheduleRes;
import com.pers.ScheduleSystem.dao.mapper.ScheduleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/3/6 11:31
 * @version: 1.0
 */
@Repository
public class ScheduleResImpl implements ScheduleRes {

    @Autowired
    ScheduleInfoMapper scheduleInfoMapper;

    @Override
    public List<SchedulePo> getAllSchedules(Long userId){
        return scheduleInfoMapper.getAllSchedules(userId);
    }

    @Override
    public List<SchedulePo> getSchedulesByState(int state, Long userId){
        return scheduleInfoMapper.getSchedulesByState(state, userId);
    }

    @Override
    public void addSchedule(SchedulePo schedulePo){
        scheduleInfoMapper.addSchedule(schedulePo);
    }

    @Override
    public void updateSchedule(SchedulePo schedulePo){
        scheduleInfoMapper.updateSchedule(schedulePo);
    }

    @Override
    public SchedulePo getSchedulesById(Long scheduleId){
        return scheduleInfoMapper.getSchedulesById(scheduleId);
    }

    public void deleteSchedule(Long id){
        scheduleInfoMapper.deleteSchedule(id);
    }

    public List<SchedulePo> getAllUserSchedules(){
        return scheduleInfoMapper.getAllUserSchedules();
    }

}
