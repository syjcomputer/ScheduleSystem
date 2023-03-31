package com.pers.ScheduleSystem.utils.converter;

/**
 * <b>BaseConverter</b>
 *
 * @author syj
 * @version 1.0
 */
public interface BaseTypeConverter {

    default Boolean integerToBoolean(Integer value) {
        if (value == null) {
            return null;
        }
        return 0 != value;
    }

    default Integer booleanToInteger(Boolean value) {
        if (value == null) {
            return null;
        }
        return value ? 1 : 0;
    }

    default Long stringToLong(String value) {
        if (value == null) {
            return null;
        }
        return Long.valueOf(value);
    }

    default String longToString(Long value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
