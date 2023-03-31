package com.pers.ScheduleSystem.dao.mapper;

import com.pers.ScheduleSystem.dao.Po.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName: UserInfoMapper
 * @Description:
 * @Author: syj
 * @Date: 2023/2/10
 * @Version: 1.0
 */
@Mapper
public interface UserInfoMapper {

    public UserPo getUserByName(String name);

    public void addUser(UserPo userPo);

    public void updateUser(UserPo userPo);

    public void delUser(Long id);

    public List<UserPo> getAllUsers();
}
