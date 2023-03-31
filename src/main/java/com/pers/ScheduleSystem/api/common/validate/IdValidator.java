package com.pers.ScheduleSystem.api.common.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Id格式验证器</b>
 *
 * @author lq
 * @version 1.0
 */
// ConstraintValidator<>参数第一个是自定义注解，第二个是需要校验的数据类型
public class IdValidator implements ConstraintValidator<IdValidate, String> {

    private boolean nullable;

    @Override
    public void initialize(IdValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (nullable && s == null) {
            return true;
        }
        try {
            Long.valueOf(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
