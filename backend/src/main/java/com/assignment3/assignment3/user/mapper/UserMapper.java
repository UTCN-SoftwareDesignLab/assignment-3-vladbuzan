package com.assignment3.assignment3.user.mapper;

import com.assignment3.assignment3.user.dto.UserComplexDto;
import com.assignment3.assignment3.user.dto.UserDisplayDto;
import com.assignment3.assignment3.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDisplayDto userDisplayDtoFromUser(User user);
    @Mapping(target = "role", expression = "java(user.getRole().getName().name())")
    UserComplexDto userComplexFromUser(User user);
}
