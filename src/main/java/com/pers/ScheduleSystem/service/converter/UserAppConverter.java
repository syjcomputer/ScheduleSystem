package com.pers.ScheduleSystem.service.converter;

import com.pers.ScheduleSystem.dao.Po.UserPo;
import com.pers.ScheduleSystem.service.dto.UserInfoDto;
import com.pers.ScheduleSystem.service.enums.GenderEnum;
import com.pers.ScheduleSystem.utils.converter.EnumConverterHelper;
import org.mapstruct.Mapper;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/3/28 17:21
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface UserAppConverter {

    default GenderEnum toGenderEnum(Integer value) {
        return EnumConverterHelper.integerToEnum(value, GenderEnum.class);
    }

    default Integer genderEnumToInteger(GenderEnum e) {
        return e != null ? e.ordinal() : null;
    }

    UserInfoDto toUserInfoDto(UserPo po);

    UserPo toUserPo(UserInfoDto dto);

}