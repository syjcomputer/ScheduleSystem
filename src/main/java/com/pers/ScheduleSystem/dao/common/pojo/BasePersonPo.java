package com.pers.ScheduleSystem.dao.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/3/27 12:53
 * @version: 1.0
 */

@Data
@Component
@EqualsAndHashCode()  // 此注解会生成equals(Object other) 和 hashCode()方法。
public class BasePersonPo {

    @TableField
    private Long id;

    @TableField
    private String name;

    @TableField
    private String pwd;
}
