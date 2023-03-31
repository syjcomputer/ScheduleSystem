package com.pers.ScheduleSystem.api.common.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <b>枚举字符串格式验证</b>
 * <p>
 *     若传入非枚举类型则该注解不生效
 * </p>
 *
 * @author lq
 * @version 1.0
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented // 在用javadoc命令生成API文档后，文档里会出现该注解说明
@Constraint(validatedBy = {EnumStringValidator.class})
public @interface EnumStringValidate {

    Class<? extends Enum<?>> value();

    String message() default "";    // 错误信息

    boolean nullable() default false;

    Class<?>[] groups() default {}; //进行分组验证

    Class<? extends Payload>[] payload() default {};

}
