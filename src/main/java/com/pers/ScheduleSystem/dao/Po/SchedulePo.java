package com.pers.ScheduleSystem.dao.Po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description: 日程Po
 * @author: sunyujie
 * @time: 2023/3/6 11:02
 * @version: 1.0
 */
@Data
@Component
@ToString(callSuper = true)
@EqualsAndHashCode()
public class SchedulePo {

    @TableField
    private Long scheduleId;

    @TableField
    private Long userId;

    @TableField
    private LocalDateTime reminderTime;

    @TableField
    private LocalDateTime ddl;

    @TableField
    private String context;

    @TableField
    private String title;

    @TableField
    private Integer state;  // 0 represents undue; 1 represents remind; 2 represents finished; 3 represents overdue
}
