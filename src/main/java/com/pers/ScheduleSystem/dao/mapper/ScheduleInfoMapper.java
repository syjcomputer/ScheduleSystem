package com.pers.ScheduleSystem.dao.mapper;

import com.pers.ScheduleSystem.dao.Po.SchedulePo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import java.util.List;

/**
 * @InterfaceName: ScheduleInfoMapper
 * @Description:
 * @Author: syj
 * @Date: 2023/3/6
 * @Version: 1.0
 */
@Mapper
public interface ScheduleInfoMapper {

    public List<SchedulePo> getAllSchedules(Long userId);

    public List<SchedulePo> getSchedulesByState(int state, Long userId);

    public SchedulePo getSchedulesById(Long scheduleId);

    public List<SchedulePo> getAllUserSchedules();

    public void addSchedule(SchedulePo schedulePo);

    public void updateSchedule(SchedulePo schedulePo);

    public void deleteSchedule(Long id);
}
