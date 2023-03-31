package com.pers.ScheduleSystem.service.impl;

import com.pers.ScheduleSystem.dao.Po.SchedulePo;
import com.pers.ScheduleSystem.dao.Po.UserPo;
import com.pers.ScheduleSystem.dao.Po.adminPo;
import com.pers.ScheduleSystem.dao.ScheduleRes;
import com.pers.ScheduleSystem.service.ScheduleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:日程服务层
 * @author: sunyujie
 * @time: 2023/3/6 11:36
 * @version: 1.0
 */
@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService {

    @Autowired
    ScheduleRes scheduleRes;

    @Autowired
    HttpSession session;

//    /**
//     * Query schedules by state and date for the active user.
//     * @param state
//     * @return
//     */
//    @Override
//    public List<SchedulePo> findSchedulesByStates(int state, String date){
//        UserPo userPo = (UserPo)session.getAttribute("loginUser");
//
//        List<SchedulePo> allSchedules = scheduleRes.getSchedulesByState(state, userPo.getUserId());
//
//        return allSchedules;
//    }

    /**
     * Query schedules by some conditions.
     * @param type
     * @param datas
     * @return
     */
    @Override
    public List<SchedulePo> querySchedulesByType(String type, Map<String, String> datas){
        int identity = (int)session.getAttribute("identity");

        List<SchedulePo> allSchedules = new ArrayList<SchedulePo>();
        if(identity==1){
            UserPo userPo = (UserPo)session.getAttribute("loginUser");
            allSchedules = scheduleRes.getAllSchedules(userPo.getId());
        }else {
            allSchedules = scheduleRes.getAllUserSchedules();
        }

//        UserPo userPo = (UserPo)session.getAttribute("loginUser");
//
//        List<SchedulePo> allSchedules = scheduleRes.getAllSchedules(userPo.getId());

        List<SchedulePo> schedulePos = new ArrayList<SchedulePo>();

        // Query by different conditions
        for(SchedulePo schedulePo: allSchedules){

            // query by date
            if(type.equals("date")){

                String begin = datas.get("begin");
                String end = datas.get("end");

                LocalDateTime  begin2 = LocalDate.parse(begin, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
                LocalDateTime end2 = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();

                if(schedulePo.getDdl().isBefore(end2) && schedulePo.getDdl().isAfter(begin2)){
                    schedulePos.add(schedulePo);
                }

            } else if (type.equals("month")) {

                String month = datas.get("month");

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime begin = LocalDateTime.parse(month+"-01 00:00:00", df);
                LocalDateTime end = begin.plusMonths(1);

                if(schedulePo.getDdl().isBefore(end) && schedulePo.getDdl().isAfter(begin)){
                    schedulePos.add(schedulePo);
                }

            } else if (type.equals("state")) {
                int state = Integer.valueOf(datas.get("state"));

                // process and update a new state to show.
                // 1. review state as overdue; 2. review state as remind
                LocalDateTime now = LocalDateTime.now();
                if(schedulePo.getState()!=2){
                    if(schedulePo.getDdl().isBefore(now)){
                        if(schedulePo.getState()!=2 && schedulePo.getState()!=3){
                            schedulePo.setState(3);
                            scheduleRes.updateSchedule(schedulePo);
                        }
                    } else if(schedulePo.getReminderTime().isBefore(now)){
                        if(schedulePo.getState()!=1){
                            schedulePo.setState(1);
                            scheduleRes.updateSchedule(schedulePo);
                        }
                    }
                }

                if(schedulePo.getState()==state){
                    schedulePos.add(schedulePo);
                }

            }

        }
        return schedulePos;
    }

    /**
     * Query schedules by a certain date.
     * @param date, i.e."yyyyMMdd"
     * @return
     */
    @Override
    public List<SchedulePo> findSchedulesByDate(String  date){
        int identity = (int)session.getAttribute("identity");

        List<SchedulePo> allSchedules = new ArrayList<SchedulePo>();
        if(identity==1){
            UserPo userPo = (UserPo)session.getAttribute("loginUser");
            allSchedules = scheduleRes.getAllSchedules(userPo.getId());
        }else {
            allSchedules = scheduleRes.getAllUserSchedules();
        }

        List<SchedulePo> schedulePosByDate = new ArrayList<SchedulePo>();
        for(SchedulePo schedulePo: allSchedules){
            LocalDateTime remindDate = schedulePo.getDdl();

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

            String date2 = df.format(remindDate);

            // add schedulePo with a certain date
            if(date.equals(date2)){

                // process and update a new state to show.
                // 1. review state as overdue; 2. review state as remind
                LocalDateTime now = LocalDateTime.now();
                if(schedulePo.getState()!=2){
                    if(schedulePo.getDdl().isBefore(now)){
                        if(schedulePo.getState()!=2 && schedulePo.getState()!=3){
                            schedulePo.setState(3);
                            scheduleRes.updateSchedule(schedulePo);
                        }
                    } else if(schedulePo.getReminderTime().isBefore(now)){
                        if(schedulePo.getState()!=1){
                            schedulePo.setState(1);
                            scheduleRes.updateSchedule(schedulePo);
                        }
                    }
                }

                schedulePosByDate.add(schedulePo);
            }
        }

        return schedulePosByDate;
    }

    /**
     * Add a new schedule.
     * @param schedulePo
     */
    @Override
    public void addSchedule(SchedulePo schedulePo){
        UserPo userPo = (UserPo)session.getAttribute("loginUser");

        schedulePo.setUserId(userPo.getId());

        scheduleRes.addSchedule(schedulePo);
    }

    /**
     * Update the certain schedule with the same id.
     * @param schedulePo
     */
    @Override
    public void updateSchedule(SchedulePo schedulePo){
        scheduleRes.updateSchedule(schedulePo);
    }

    /**
     * 根据id查询Schedule.
     * @param id
     * @return
     */
    @Override
    public SchedulePo getSchedule(Long id){
        return scheduleRes.getSchedulesById(id);
    }

    /**
     * 修改日程信息
     * @param datas
     * @param schedulePo
     */
    @Override
    public void reviewSchedule(Map<String, String> datas, SchedulePo schedulePo){

        int identity = (int)session.getAttribute("identity");

        if(identity==2){
            adminPo admin = (adminPo) session.getAttribute("loginUser");
            if(admin.getAccess()==1){
                // Exception
            }
        }

        String content = datas.get("content");
        String ddl = datas.get("ddl");
        String remind = datas.get("remind");
        String review = datas.get("review");
        String title = datas.get("title");
//        System.out.println("title"+title);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ddl2 = LocalDateTime.parse(ddl, df);
        LocalDateTime remind2 = LocalDateTime.parse(remind, df);

        schedulePo.setDdl(ddl2);
        schedulePo.setReminderTime(remind2);
        schedulePo.setContext(content);
        schedulePo.setTitle(title);

        if(schedulePo.getState()==1){
            if(!(review.equals(null))){
                schedulePo.setState(2);
            }
        } else if (schedulePo.getState()==3) {
            if(!(review.equals(null))){
                schedulePo.setState(2);
            }
        }

        scheduleRes.updateSchedule(schedulePo);

    }

    /**
     * 根据id删除日程。
     * @param id
     */
    @Override
    public void deleteSchedule(Long id){
        int identity = (int)session.getAttribute("identity");

        if(identity==2){
            adminPo admin = (adminPo) session.getAttribute("loginUser");
            if(admin.getAccess()==1){
                // Exception
            }
        }

        scheduleRes.deleteSchedule(id);
    }

    /**
     * 获取所有日程
     * @return
     */
    @Override
    public List<SchedulePo> getAllSchedules(){
        return scheduleRes.getAllUserSchedules();
    }

}
