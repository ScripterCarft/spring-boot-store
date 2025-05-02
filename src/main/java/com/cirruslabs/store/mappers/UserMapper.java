package com.cirruslabs.store.mappers;

import com.cirruslabs.store.dtos.RegisterUserRequest;
import com.cirruslabs.store.dtos.UpdateUserRequest;
import com.cirruslabs.store.dtos.UserDto;
import com.cirruslabs.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);

    void updateUser(UpdateUserRequest request, @MappingTarget User user);
}