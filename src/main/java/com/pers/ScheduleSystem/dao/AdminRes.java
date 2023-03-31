package com.pers.ScheduleSystem.dao;

import com.pers.ScheduleSystem.dao.Po.adminPo;

/**
 * @InterfaceName: AdminRes
 * @Description:
 * @Author: syj
 * @Date: 2023/3/27
 * @Version: 1.0
 */
public interface AdminRes {

    public adminPo getAdminByName(String name);
}
