package com.pers.ScheduleSystem.api;

import com.pers.ScheduleSystem.dao.Po.UserPo;
import com.pers.ScheduleSystem.dao.Po.adminPo;
import com.pers.ScheduleSystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/3/27 21:57
 * @version: 1.0
 */
@Controller
public class UserHandler {

    @Autowired
    UserInfoService userInfoService;

    /**
     * Update password
     * @param request
     * @param response
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public void updateUser(HttpServletRequest request, HttpServletResponse response){
        String pwd = request.getParameter("loginpw");

        userInfoService.updateUser(pwd);

    }

    /**
     * 注销登陆
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String exit(HttpServletRequest request, HttpServletResponse response){

        try {
            request.getSession().removeAttribute("loginUser");

            return "1";

        }catch (Exception e){
            return "0";
        }

    }

    /**
     * 获取用户列表用于选择删除
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delUser")
    public ModelAndView deleteUser(HttpServletRequest request,
                                   HttpServletResponse response){

        List<UserPo> allUsers =  userInfoService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView("admin/deleteUser");

        modelAndView.addObject("allUsers", allUsers);

        return modelAndView;
    }

    /**
     * 获取用户列表用于选择删除
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delOneUser", method = RequestMethod.GET)
    public ModelAndView deleteOneUser(HttpServletRequest request,
                                   HttpServletResponse response){

        Long id = Long.valueOf(request.getParameter("id"));

        ModelAndView modelAndView = new ModelAndView();

        adminPo admin = (adminPo) request.getSession().getAttribute("loginUser");

        System.out.println(admin);
        if(admin.getAccess()==1){
            System.out.println("yes");
            modelAndView.setViewName("admin/success");
            modelAndView.addObject("message","无权限删除");
            return modelAndView;
        }

        try{
            userInfoService.delUser(id);

            modelAndView.setViewName("admin/success");
            modelAndView.addObject("message","删除用户成功");
//            modelAndView.addObject("path", "")

        }catch (Exception e){
            modelAndView.setViewName("error");
            modelAndView.addObject("message","删除用户失败");
        }

        return modelAndView;
    }
}
