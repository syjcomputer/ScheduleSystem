package com.pers.ScheduleSystem.service;

import com.pers.ScheduleSystem.dao.Po.SchedulePo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: ScheduleInfoService
 * @Description:
 * @Author: syj
 * @Date: 2023/3/6
 * @Version: 1.0
 */
public interface ScheduleInfoService {

//    public List<SchedulePo> findSchedulesByStates(int state, String date);

    public List<SchedulePo> querySchedulesByType(String type, Map<String, String> datas);

    public List<SchedulePo> findSchedulesByDate(String date);

    public void addSchedule(SchedulePo schedulePo);

    public void updateSchedule(SchedulePo schedulePo);

    public SchedulePo getSchedule(Long id);

    public void reviewSchedule(Map<String, String> datas, SchedulePo schedulePo);

    public void deleteSchedule(Long id);

    public List<SchedulePo> getAllSchedules();

}
