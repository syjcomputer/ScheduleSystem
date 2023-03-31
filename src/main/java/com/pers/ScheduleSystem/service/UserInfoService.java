package com.pers.ScheduleSystem.service;

import com.pers.ScheduleSystem.dao.Po.UserPo;
import com.pers.ScheduleSystem.dao.Po.adminPo;

import java.util.List;

/**
 * @InterfaceName: UserInfoService
 * @Description:
 * @Author: syj
 * @Date: 2023/2/10
 * @Version: 1.0
 */
public interface UserInfoService {

    public UserPo getUserByName(String name);

    public adminPo getAdminByName(String name);

    public void addUser(UserPo userPo);

    public void updateUser(String pwd);

    public List<UserPo> getAllUsers();

    public void delUser(Long id);
}
