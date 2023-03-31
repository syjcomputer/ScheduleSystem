package com.pers.ScheduleSystem.service.impl;

import com.pers.ScheduleSystem.dao.AdminRes;
import com.pers.ScheduleSystem.dao.Po.UserPo;
import com.pers.ScheduleSystem.dao.Po.adminPo;
import com.pers.ScheduleSystem.dao.UserRes;
import com.pers.ScheduleSystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description:用户服务层
 * @author: sunyujie
 * @time: 2023/2/10 23:17
 * @version: 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserRes userRes;

    @Autowired
    AdminRes adminRes;

    @Autowired
    HttpSession session;

    /**
     * 查询用户信息
     * @param name
     * @return
     */
    @Override
    public UserPo getUserByName(String name){
        return userRes.getUserByName(name);
    }

    /**
     * 添加用户
     * @param userPo
     */
    @Override
    public void addUser(UserPo userPo) {

        UserPo userPo1 = userRes.getUserByName(userPo.getName());

        if(userPo1.getId()==null){
            userRes.addUser(userPo);
        }else {
            // Exception

        }
    }

    /**
     * 修改用户密码
     * @param pwd
     */
    @Override
    public void updateUser(String pwd){
        UserPo userPo = (UserPo)session.getAttribute("loginUser");

        userPo.setPwd(pwd);

        userRes.updateUser(userPo);
    }

    /**
     * 通过名字获取管理员
     * @param name
     * @return
     */
    @Override
    public adminPo getAdminByName(String name){
        return adminRes.getAdminByName(name);
    }

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<UserPo> getAllUsers() {
        return userRes.getAllUsers();
    }

    @Override
    public void delUser(Long id){

        adminPo admin = (adminPo)session.getAttribute("loginUser");

//        userRes.delUser(id);
        if(admin.getAccess()==0){
            userRes.delUser(id);
        }else {
            // Exception
        }
    }
}
