package com.karacamehmet.karacablog.service.mapper;

import com.karacamehmet.karacablog.dto.request.RegisterRequest;
import com.karacamehmet.karacablog.dto.response.GetUserResponse;
import com.karacamehmet.karacablog.dto.response.UpdateUserResponse;
import com.karacamehmet.karacablog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    User getUserFromRegisterRequest(RegisterRequest registerRequest);

    GetUserResponse getUserResponseFromUser(User user);

    UpdateUserResponse getUpdateUserResponseFromUser(User user);
}
