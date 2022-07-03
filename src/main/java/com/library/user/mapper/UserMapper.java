package com.library.user.mapper;

import com.library.address.model.AddressResponseDto;
import com.library.user.entity.User;
import com.library.user.model.UserRequestDto;
import com.library.user.model.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@MapperConfig
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto convertToUserResponseDto(UserRequestDto userRequestDto);

    @Mapping(ignore = true, target = "addresses")
    User convertToUser(UserRequestDto userRequestDto);

    @Mapping(target = "userAddresses", source = "list")
    UserResponseDto convertFromUserToUserResponseDto(User user, List<AddressResponseDto> list);

    UserResponseDto convertToUserResponseDto(User user);
}
