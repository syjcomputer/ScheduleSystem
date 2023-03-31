package com.pers.ScheduleSystem.utils.spring;

import com.pers.ScheduleSystem.ScheduleSystemApplication;

/**
 * @InterfaceName: GlobalBeanGetter
 * @Description:
 * @Author: syj
 * @Date: 2023/3/28
 * @Version: 1.0
 */
public interface GlobalBeanGetter {

    /**
     * 获取 spring 全局单例 bean
     */
    default <T> T getInstance(Class<T> tClass) {
        return ScheduleSystemApplication.getContext().getBean(tClass);
    }

}