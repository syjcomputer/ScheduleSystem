package com.pers.ScheduleSystem.dao;

import com.pers.ScheduleSystem.dao.Po.UserPo;

import java.util.List;

/**
 * @InterfaceName: UserRes
 * @Description:
 * @Author: syj
 * @Date: 2023/2/10
 * @Version: 1.0
 */
public interface UserRes {

    public UserPo getUserByName(String name);

    public void addUser(UserPo userPo);

    public void updateUser(UserPo userPo);

    public void delUser(Long id);

    public List<UserPo> getAllUsers();
}
