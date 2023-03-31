package com.pers.ScheduleSystem.dao.Po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.pers.ScheduleSystem.dao.common.pojo.BasePersonPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @description: 用户Po
 * @author: sunyujie
 * @time: 2023/2/10 20:38
 * @version: 1.0
 */
@Data
@Component
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserPo extends BasePersonPo {

//    @TableField
//    private Long id;
//
//    @TableField
//    private String name;
//
//    @TableField
//    private String pwd;

    @TableField
    private String telephone;
}
