package com.pers.ScheduleSystem.dao.impl;

import com.pers.ScheduleSystem.dao.Po.UserPo;
import com.pers.ScheduleSystem.dao.UserRes;
import com.pers.ScheduleSystem.dao.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:用户repository
 * @author: sunyujie
 * @time: 2023/2/10 21:29
 * @version: 1.0
 */
@Repository
public class UserResImpl implements UserRes {

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 通过name获取用户信息
     * @param name
     * @return
     */
    @Override
    public UserPo getUserByName(String name){
        UserPo userPo = userInfoMapper.getUserByName(name);
        return userPo;
    }

    /**
     * 添加用户
     * @param userPo
     */
    @Override
    public void addUser(UserPo userPo){
        System.out.println(userPo);
        userInfoMapper.addUser(userPo);
    }

    /**
     * 修改用户信息
     * @param userPo
     */
    @Override
    public void updateUser(UserPo userPo){
        userInfoMapper.updateUser(userPo);
    }

    /**
     * 删除指定用户
     * @param id
     */
    @Override
    public void delUser(Long id){
        userInfoMapper.delUser(id);
    }

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<UserPo> getAllUsers(){
        return userInfoMapper.getAllUsers();
    }
}
