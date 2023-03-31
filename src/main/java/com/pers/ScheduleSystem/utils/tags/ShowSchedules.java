package com.pers.ScheduleSystem.utils.tags;

import com.pers.ScheduleSystem.dao.Po.SchedulePo;
import com.pers.ScheduleSystem.dao.Po.UserPo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description: Show list of schedules for a certain date.
 * @author: sunyujie
 * @time: 2023/3/6 13:10
 * @version: 1.0
// */
public class ShowSchedules extends SimpleTagSupport {

//    private List<SchedulePo> schedules;
//
//    public void setSchedules(String allSchedules) {
//
////        this.schedules = (List<SchedulePo>)allSchedules;
//        System.out.println("set"+allSchedules);
//    }

    @Override
    public void doTag()
            throws JspException, IOException
    {

        HttpServletRequest request=(HttpServletRequest) ((PageContext)this.getJspContext()).getRequest();

//        LocalDateTime date = (LocalDateTime) request.getAttribute("selectedDate");
        List<SchedulePo> undue = (List<SchedulePo>) request.getAttribute("undue");
        List<SchedulePo> remind  = (List<SchedulePo>) request.getAttribute("remind");
        List<SchedulePo> finished = (List<SchedulePo>) request.getAttribute("finished");
        List<SchedulePo> overdue = (List<SchedulePo>) request.getAttribute("overdue");
//        String identity = (String) request.getSession().getAttribute("identity");
//
//        if (Objects.equals(identity,"1")){
//            UserPo userPo = (UserPo) request.getSession().getAttribute("loginUser");
//
//        }

        String path = request.getContextPath();

        JspWriter out = getJspContext().getOut();
        String scheduleList ="";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Map<String,List<SchedulePo>> stringListHashMap = new HashMap<String, List<SchedulePo>>();
        stringListHashMap.put("提醒事项", remind);
        stringListHashMap.put("待办事项", undue);
        stringListHashMap.put("过期事项",overdue);
        stringListHashMap.put("已完成事项", finished);

        for(Map.Entry<String, List<SchedulePo>> entry:stringListHashMap.entrySet()){
            out.println("<p>"+entry.getKey()+":<table class='table table-bordered text-center'><tbody>" +
                    "<tr><th>标题</th><th>内容</th><th>截止时间</th><th>提醒时间</th><th>操作</th></tr>");

            for(SchedulePo schedulePo:entry.getValue()){
                String ddl = df.format(schedulePo.getDdl());
                String times = df.format(schedulePo.getReminderTime());
                out.println("<tr><td>"+schedulePo.getTitle()+"</td><td>"+schedulePo.getContext()+"</td><td>"+ddl+"</td><td>"+times+"</td>" +
                        "<td><a href='"+ path + "/editSchedules?id="+schedulePo.getScheduleId()+"'>编辑</a></td></tr>");
            }
            out.println("</tbody></table></p>");

        }



//        // remind
//        out.println("<p>提醒事项：<table class='table table-bordered text-center'><tbody>" +
//                "<tr><th>标题</th><th>内容</th><th>截止时间</th><th>提醒时间</th><th>操作</th></tr>");
//        for(SchedulePo schedulePo:remind){
//            String ddl = df.format(schedulePo.getDdl());
//            String times = df.format(schedulePo.getReminderTime());
//            out.println("<tr><td>"+schedulePo.getTitle()+"</td><td>"+schedulePo.getContext()+"</td><td>"+ddl+"</td><td>"+times+"</td>" +
//                    "<td><a href='"+ path + "/editSchedules?id="+schedulePo.getScheduleId()+"'>编辑</a></td></tr>");
//        }
//        out.println("</tbody></table></p>");
//
//        // undue
//        out.println("<p>待办事项：<table class='table table-bordered text-center'><tbody>" +
//                "<tr><th>标题</th><th>内容</th><th>截止时间</th><th>提醒时间</th><th>操作</th></tr>");
//        for(SchedulePo schedulePo:undue){
//            String ddl = df.format(schedulePo.getDdl());
//            String times = df.format(schedulePo.getReminderTime());
//            out.println("<tr><td>"+schedulePo.getTitle()+"</td><td>"+schedulePo.getContext()+"</td><td>"+ddl+"</td><td>"+times+"</td>" +
//                    "<td><a href='"+ path + "/editSchedules?id="+schedulePo.getScheduleId()+"'>编辑</a></td></tr>");
//        }
//        out.println("</tbody></table></p>");
//
//        // finished
//        out.println("<p>已完成事项：<table class='table table-bordered text-center '><tbody>" +
//                "<tr><th>标题</th><th>内容</th><th>截止时间</th><th>提醒时间</th><th>操作</th></tr>");
//        for(SchedulePo schedulePo:finished){
//            String ddl = df.format(schedulePo.getDdl());
//            String times = df.format(schedulePo.getReminderTime());
//            out.println("<tr><td>"+schedulePo.getTitle()+"</td><td>"+schedulePo.getContext()+"</td><td>"+ddl+"</td><td>"+times+"</td>" +
//                    "<td><a href='"+ path + "/editSchedules?id="+schedulePo.getScheduleId()+"'>编辑</a></td></tr>");
//        }
//        out.println("</tbody></table></p>");
//
//        // overdue
//        out.println("<p>过期事项：<table class='table table-bordered text-center'><tbody>" +
//                "<tr><th>标题</th><th>内容</th><th>截止时间</th><th>提醒时间</th><th>操作</th></tr>");
//        for(SchedulePo schedulePo:overdue){
//            String ddl = df.format(schedulePo.getDdl());
//            String times = df.format(schedulePo.getReminderTime());
//            out.println("<tr><td>"+schedulePo.getTitle()+"</td><td>"+schedulePo.getContext()+"</td><td>"+ddl+"</td><td>"+times+"</td>" +
//                    "<td><a href='"+ path + "/editSchedules?id="+schedulePo.getScheduleId()+"'>编辑</a></td></tr>");
//        }
//        out.println("</tbody></table></p>");

    }
}
