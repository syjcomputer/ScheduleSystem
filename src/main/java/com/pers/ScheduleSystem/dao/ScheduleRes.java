package com.pers.ScheduleSystem.dao;

import com.pers.ScheduleSystem.dao.Po.SchedulePo;

import java.util.List;

/**
 * @InterfaceName: ScheduleRes
 * @Description:
 * @Author: syj
 * @Date: 2023/3/6
 * @Version: 1.0
 */
public interface ScheduleRes {
    public List<SchedulePo> getAllSchedules(Long userId);

    public List<SchedulePo> getSchedulesByState(int state, Long userId);

    public SchedulePo getSchedulesById(Long scheduleId);

    public List<SchedulePo> getAllUserSchedules();

    public void addSchedule(SchedulePo schedulePo);

    public void updateSchedule(SchedulePo schedulePo);

    public void deleteSchedule(Long id);
}
