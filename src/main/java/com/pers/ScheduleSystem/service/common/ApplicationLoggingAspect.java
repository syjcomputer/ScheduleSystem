package com.pers.ScheduleSystem.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <b>服务层日志切面</b>
 *
 * @author lq
 * @version 1.0
 */
@Component
@Aspect // AOP编程
@Slf4j
public class ApplicationLoggingAspect {

    @Autowired
    private ObjectMapper objectMapper;

    // 匹配com.pers.ScheduleSystem.service.impl包下所有的类的方法
    @Pointcut("execution(* com.pers.ScheduleSystem.service.impl.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("进入服务: {}.{}, 入参 {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                objectMapper.writeValueAsString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();    // 执行主方法体，返回主方法体结果
        log.info("离开服务: {}.{}, 出参 {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                objectMapper.writeValueAsString(result));
        return result;
    }

}
