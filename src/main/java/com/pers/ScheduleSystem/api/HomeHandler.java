package com.pers.ScheduleSystem.api;

import com.pers.ScheduleSystem.dao.common.pojo.BasePersonPo;
import com.pers.ScheduleSystem.dao.Po.UserPo;
import com.pers.ScheduleSystem.dao.Po.adminPo;
import com.pers.ScheduleSystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @description:主页控制层
 * @author: sunyujie
 * @time: 2022/12/21 16:59
 * @version: 1.0
 */
@Controller
public class HomeHandler {

    @Autowired
    UserInfoService userInfoService;


    /**
     * 用户注册
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {

        // get infos
        String name = request.getParameter("loginname");
        String pwd = request.getParameter("loginpw");
        String telephone = request.getParameter("telephone");

        UserPo userPo = new UserPo();
        userPo.setName(name);
        userPo.setPwd(pwd);
        userPo.setTelephone(telephone);

        try{
            userInfoService.addUser(userPo);
        }catch (Exception e){
            e.printStackTrace();
        }

        ModelAndView model = new ModelAndView();
        model.setViewName("loginSuccess");

        request.getSession().setAttribute("loginUserNums", 0);

        return model;
    }

    /**
     * 用户或管理员登录
     * @param name
     * @param request
     * @return
     */
    @RequestMapping(value="/userLogin", method= RequestMethod.POST)
    public ModelAndView userLogin(@RequestParam("userName") String name,
                               HttpServletRequest request, HttpServletResponse response) {
        System.out.println("提交成功");

        String identity = request.getParameter("identity");
        ModelAndView model = new ModelAndView();

        if (name == null || "".equals(name)) {
            //logger.error("教师账号为空");
            request.setAttribute("error", "登录信息有误");
            model.setViewName("error");
            // return "/error";
            return model;
        }

        BasePersonPo basePersonPo = new BasePersonPo();
        if(Objects.equals(identity,"1")){
            UserPo userPo = userInfoService.getUserByName(name);
            basePersonPo = (BasePersonPo) userPo;

            //将当前登录用户存入 Session
            request.getSession().setAttribute("loginUser", userPo);
            request.getSession().setAttribute("identity", 1);

        } else if (Objects.equals(identity, "2")) {
            adminPo admin = userInfoService.getAdminByName(name);
            basePersonPo = (BasePersonPo) admin;

            //将当前登录用户存入 Session
            request.getSession().setAttribute("loginUser", admin);
            request.getSession().setAttribute("identity", 2);

        }else {
            request.setAttribute("error", "不合法身份！");
            model.setViewName("error");
            return model;
        }

        //获取当前登录人
//        UserPo userPo = userInfoService.getUserByName(name);

        if(basePersonPo.getId() == null){
            //logger.error("教师账号为空");
            request.setAttribute("error", "账号不存在！");
            model.setViewName("error");
            return model;
        }
        String pwd = request.getParameter("userPw");

        if(!Objects.equals(basePersonPo.getPwd(),pwd)){
            request.setAttribute("error", "密码错误！");
            model.setViewName("error");
            return model;
        }

        //将当前登录用户存入 Session
//        request.getSession().setAttribute("loginUser", basePersonPo);
        int nums = 0;
        if(request.getSession().getAttribute("loginUserNums")!=null){
            nums = (int)request.getSession().getAttribute("loginUserNums");
        }
        request.getSession().setAttribute("loginUserNums", nums+1);

        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie("cookie_username", basePersonPo.getName());
        // 设置cookie的持久化时间，30天
        cookie_username.setMaxAge(30 * 24 * 60 * 60);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);

        //System.out.println(nums);
        model.setViewName("admin/success");

        model.addObject("message","登录成功");
        if(Objects.equals(identity, "1")){
            model.addObject("path", "admin/menu.jsp");
        } else if (Objects.equals(identity, "2")) {
            model.addObject("path", "admin/adminMenu.jsp");
        }else {
            // Exception
        }


        return model;
    }


}