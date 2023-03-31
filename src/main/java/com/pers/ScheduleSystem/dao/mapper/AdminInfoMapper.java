package com.pers.ScheduleSystem.dao.mapper;

import com.pers.ScheduleSystem.dao.Po.adminPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName: AdminInfoMapper
 * @Description:
 * @Author: syj
 * @Date: 2023/3/27
 * @Version: 1.0
 */
@Mapper
public interface AdminInfoMapper {

    public adminPo getAdminByName(String name);
}
