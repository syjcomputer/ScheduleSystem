package com.pers.ScheduleSystem.dao.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <b>Base 数据库 PO</b>
 *
 * @author lq
 * @version 1.0
 */
@Data
@EqualsAndHashCode(exclude = {"createTime", "updateTime"})  // 此注解会生成equals(Object other) 和 hashCode()方法。
public class BaseDatabasePo implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID) // 生成主键策略
    protected Long id;

    /**
     * 创建时间
     */
    @TableField
    protected LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField
    protected LocalDateTime updateTime;

}
