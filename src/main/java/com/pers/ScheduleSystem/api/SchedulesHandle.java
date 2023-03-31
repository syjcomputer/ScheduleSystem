package com.pers.ScheduleSystem.api;

import com.pers.ScheduleSystem.dao.Po.SchedulePo;
import com.pers.ScheduleSystem.dao.Po.UserPo;
import com.pers.ScheduleSystem.service.ScheduleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:日程相关控制层
 * @author: sunyujie
 * @time: 2023/3/6 18:21
 * @version: 1.0
 */
@Controller
public class SchedulesHandle {

    @Autowired
    ScheduleInfoService scheduleInfoService;

    /**
     * 根据条件查询日程，包含date, month, state三种方法
     * @param response
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/querySchedule", method = RequestMethod.GET)
    public ModelAndView querySchedules(HttpServletResponse response,
                                       HttpServletRequest request) throws ServletException, IOException {

        String type = request.getParameter("type");

        Map<String, String> datas = new HashMap<String, String>();

        ModelAndView modelAndView = new ModelAndView();

        if(type.equals("date")){
            modelAndView.setViewName("admin/queryByDate");

            String begin = request.getParameter("begin");
            String end = request.getParameter("end");

            if(begin.equals("") || end.equals("")){
                // Exception
            }

            datas.put("begin", begin);
            datas.put("end", end);

            modelAndView.addObject("begin", begin);
            modelAndView.addObject("end", end);
        } else if (type.equals("month")) {
            modelAndView.setViewName("admin/queryByMonth");

            String month = request.getParameter("month");

            if(month.equals("")){
                // Exception
            }

            datas.put("month", month);

            modelAndView.addObject("month", month);
        } else if (type.equals("state")) {
            modelAndView.setViewName("admin/queryByState");

            String state = request.getParameter("states");
//            System.out.println("state"+state);

            datas.put("state", state);

            modelAndView.addObject("status",state);

            scheduleInfoService.querySchedulesByType(type, datas);


        } else {
            // Exception
        }

        List<SchedulePo> schedulePoList = scheduleInfoService.querySchedulesByType(type, datas);

        modelAndView.addObject("schedulePoList", schedulePoList);

        return modelAndView;
    }

    /**
     * 设置具体事项用于展示
     * @param response
     * @param request
     */
    @RequestMapping(value = "/schedules", method = RequestMethod.GET)
    public ModelAndView operationSchedules(HttpServletResponse response,
                                           HttpServletRequest request) throws ServletException, IOException {

        String date = request.getParameter("date");
        List<SchedulePo> allSchedules = scheduleInfoService.findSchedulesByDate(date);

        List<SchedulePo> undue = new ArrayList<SchedulePo>();
        List<SchedulePo> remind = new ArrayList<SchedulePo>();
        List<SchedulePo> finished = new ArrayList<SchedulePo>();
        List<SchedulePo> overdue = new ArrayList<SchedulePo>();

        // 分类处理
        for(SchedulePo schedulePo:allSchedules){
            switch (schedulePo.getState())
            {
                case 0:
                    undue.add(schedulePo);
                    break;
                case 1:
                    remind.add(schedulePo);
                    break;
                case 2:
                    finished.add(schedulePo);
                    break;
                case 3:
                    overdue.add(schedulePo);
                    break;
            }
        }

        request.setAttribute("undue", undue);
        request.setAttribute("remind", remind);
        request.setAttribute("finished", finished);
        request.setAttribute("overdue", overdue);
        request.setAttribute("schedules",allSchedules);

        ModelAndView modelAndView = new ModelAndView("admin/operationSchedules");
        modelAndView.addObject("date", date);

        return modelAndView; // Ajax不能跳转，只能在回调函数跳转
    }


    /**
     * Process each cell in calendar table with schedule list.
     * @param date
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping(value="/updateCalendar", method= RequestMethod.POST)
    public void updateCalendar(@RequestParam(value="date") String date,
                               HttpServletResponse response,
                               HttpServletRequest request) throws IOException {

        List<SchedulePo> allSchedules = scheduleInfoService.findSchedulesByDate(date);

        List<SchedulePo> undue = new ArrayList<SchedulePo>();
        List<SchedulePo> remind = new ArrayList<SchedulePo>();
        List<SchedulePo> finished = new ArrayList<SchedulePo>();
        List<SchedulePo> overdue = new ArrayList<SchedulePo>();

        // 分类处理
        for(SchedulePo schedulePo:allSchedules){
            switch (schedulePo.getState())
            {
                case 0:
                    undue.add(schedulePo);
                    break;
                case 1:
                    remind.add(schedulePo);
                    break;
                case 2:
                    finished.add(schedulePo);
                    break;
                case 3:
                    overdue.add(schedulePo);
                    break;
            }
        }

        String scheduleList ="";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");

        // remind
        scheduleList += "<table><tbody>";
        for(SchedulePo schedulePo:remind){
            String times = df.format(schedulePo.getDdl());
            scheduleList += "<tr><td class=\"remind-text\">"+schedulePo.getTitle()+"</td><td class=\"remind-text\">"+times+"</td></tr>";
        }
        scheduleList +="</tbody></table>";

        // undue
        scheduleList += "<table><tbody>";
        for(SchedulePo schedulePo:undue){
            String times = df.format(schedulePo.getDdl());
            scheduleList += "<tr><td class=\"undue-text\">"+schedulePo.getTitle()+"</td><td class=\"undue-text\">"+times+"</td></tr>";
        }
        scheduleList +="</tbody></table>";

        // finished
        scheduleList += "<table><tbody>";
        for(SchedulePo schedulePo:finished){
            String times = df.format(schedulePo.getDdl());
            scheduleList += "<tr><td class=\"finished-text\">"+schedulePo.getTitle()+"</td><td class=\"finished-text\">"+times+"</td></tr>";
        }
        scheduleList +="</tbody></tbody>";

        // overdue
        scheduleList += "<table><tbody>";
        for(SchedulePo schedulePo:overdue){
            String times = df.format(schedulePo.getDdl());
            scheduleList += "<tr><td class=\"overdue-text\">"+schedulePo.getTitle()+"</td><td class=\"overdue-text\">"+times+"</td></tr>";
        }
        scheduleList +="</tbody></tbody>";

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print(scheduleList);
    }

    /**
     * 添加单个日程
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/addOneSchedule", method = RequestMethod.POST)
    public ModelAndView addSchedule(
                            HttpServletResponse response,
                            HttpServletRequest request){

        String remindTime = request.getParameter("remindTime");
        String ddl = request.getParameter("ddl");
        String date = request.getParameter("today");
        String context = request.getParameter("context");
        String title = request.getParameter("title");

        ddl = date + " " + ddl;
        System.out.println(ddl);

        SchedulePo schedulePo = new SchedulePo();
        schedulePo.setContext(context);
        schedulePo.setTitle(title);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime  remindTime2 = LocalDateTime.parse(remindTime,df2);
        schedulePo.setReminderTime(remindTime2);

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime  ddl2 = LocalDateTime.parse(ddl,df);
        schedulePo.setDdl(ddl2);

        ModelAndView modelAndView = new ModelAndView("admin/success");

        // 设置状态
        if(remindTime2.isBefore(ddl2)){

            // 不合法添加
            modelAndView.addObject("message", "提醒时间早于截止时间");
            return modelAndView;
        } else if (now.isBefore(remindTime2)) {
            schedulePo.setState(0);
        } else if (now.isBefore(ddl2)) {
            schedulePo.setState(1);
        } else{
            schedulePo.setState(3);
        }

        try{
            scheduleInfoService.addSchedule(schedulePo);
            modelAndView.addObject("message", "添加日程成功");

        }catch (Exception e){
            e.printStackTrace();
            modelAndView.addObject("message", "添加日程失败");
        }finally {
            return modelAndView;
        }
    }

    /**
     * 转发到editSchedules.jsp
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/editSchedules", method = RequestMethod.GET)
    public ModelAndView editSchedules(HttpServletResponse response,
                                      HttpServletRequest request){

        Long id = Long.valueOf(request.getParameter("id"));

        SchedulePo schedulePo = scheduleInfoService.getSchedule(id);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ddl = df.format(schedulePo.getDdl());
        String remind = df.format(schedulePo.getReminderTime());

        ModelAndView modelAndView = new ModelAndView("admin/editSchedules");

        modelAndView.addObject("id", id);
        modelAndView.addObject("content", schedulePo.getContext());
        modelAndView.addObject("ddl", ddl);
        modelAndView.addObject("remind", remind);
        modelAndView.addObject("state", schedulePo.getState());
        modelAndView.addObject("title", schedulePo.getTitle());

        return modelAndView;
    }

    /**
     * Review details of a certain schedule.
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/reviewSchedule", method = RequestMethod.POST)
    public ModelAndView reviewSchedule(HttpServletResponse response,
                                       HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("admin/success");

        Long id = Long.valueOf(request.getParameter("id"));
        SchedulePo schedulePo = scheduleInfoService.getSchedule(id);

        String content = request.getParameter("content");
        String ddl = request.getParameter("ddl");
        String remind = request.getParameter("remind");
        String title = request.getParameter("title");

        String review = null;
        if (schedulePo.getState()==1||schedulePo.getState()==3){

            review = request.getParameter("review");
        }

        Map<String, String> datas = new HashMap<String, String>();
        datas.put("content", content);
        datas.put("ddl", ddl);
        datas.put("remind", remind);
        datas.put("review", review);
        datas.put("title", title);

        try{
            scheduleInfoService.reviewSchedule(datas,schedulePo);
            modelAndView.addObject("message","修改成功");
        }catch (Exception e){
            e.printStackTrace();
            modelAndView.addObject("message", "修改失败");
        }

        return modelAndView;
    }

    /**
     * 删除日程
     * @param id
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteSchedule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteSchedule(@RequestParam("id")String id,
                                       HttpServletResponse response,
                                       HttpServletRequest request) throws IOException {
        
        Map<String, String> map= new HashMap<String, String>();

        Long id2 = Long.valueOf(id);

        SchedulePo schedulePo = scheduleInfoService.getSchedule(id2);
        if(schedulePo==null){
            // Exception
        }

        LocalDateTime ddl = schedulePo.getDdl();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String ddl2 = df.format(ddl);

        map.put("ddl",ddl2);

        String message="";
        try{
            scheduleInfoService.deleteSchedule(id2);
            message = "成功删除";
            map.put("result2","1");

//            String path = request.getContextPath();
//            modelAndView.addObject("path", path+"/schedules?date="+ddl2);
        }catch (Exception e){
            message = "删除失败";
            map.put("result2","2");

        }finally {
//            response.getWriter().print(message);
            map.put("message2", message);
        }

        return map;
    }


}
