package com.pers.ScheduleSystem.utils.converter;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * <b>枚举转换帮助类</b>
 *
 * @author syj
 * @version 1.0
 */
public class EnumConverterHelper {

    public static <T extends Enum<?>> T integerToEnum(Integer value, Class<T> enumClass) {
        return objToEnum(value, enumClass, "ordinal");  // 返回枚举类型的序数
    }

    public static <T extends Enum<?>> T stringToEnum(String value, Class<T> enumClass) {
        return objToEnum(value, enumClass, "name");  // 返回枚举类型的枚举名字
    }

    // @SneakyThrows是省略try catch代码
    // InvocationTargetException是指被吃掉的自定义异常，InvocationTargetException.getTarget()可以捕获原始异常
    @SneakyThrows({NoSuchMethodException.class, InvocationTargetException.class, IllegalAccessException.class})
    public static <T extends Enum<?>> T objToEnum(Object value, Class<T> enumClass, String methodName) {
        if (value == null) {
            return null;
        }
        Method method = enumClass.getMethod(methodName);
        for (var e : enumClass.getEnumConstants()) {
            if (method.invoke(e).equals(value)) {   // Method.invoke()是根据传入参数动态调用的方法
                return e;
            }
        }
        return null;
    }

    public static <T extends Enum<?>> T objToEnum(Object value, Class<T> enumClass, Function<T, Object> toObjFunc) {
        if (value == null) {
            return null;
        }
        for (var e : enumClass.getEnumConstants()) {
            if (toObjFunc.apply(e).equals(value)) {
                return e;
            }
        }
        return null;
    }

}
