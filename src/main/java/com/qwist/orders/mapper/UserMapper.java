package com.qwist.orders.mapper;

import com.qwist.orders.dto.UserRespDto;
import com.qwist.orders.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Takes care of mapping User, UserRespDto and lists of those
 */
@Mapper(uses = OrderMapper.class)
public interface UserMapper {

    /**
     * Maps User to UserRespDto
     * @param user User
     * @return User resp DTO
     */
    UserRespDto userToUserRespDto(User user);

    /**
     * Maps list of user to list of UserRespDto
     * @param users list of users
     * @return List of user resp DTO
     */
    List<UserRespDto> userListToUserRespDtoList(List<User> users);
}
