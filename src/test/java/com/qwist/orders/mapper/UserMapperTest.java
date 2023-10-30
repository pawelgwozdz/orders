package com.qwist.orders.mapper;

import com.qwist.orders.dto.UserRespDto;
import com.qwist.orders.entity.User;
import com.qwist.orders.utils.InitDataUserUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void shouldReturnCorrectUserData() {
        //GIVEN
        User adminUser = InitDataUserUtils.getAdminUser();

        //WHEN
        UserRespDto userRespDto = userMapper.userToUserRespDto(adminUser);

        //THEN
        assertEquals(userRespDto.getLogin(), adminUser.getLogin());
        assertEquals(userRespDto.getUserType(), adminUser.getUserType());
        assertEquals(userRespDto.getEmail(), adminUser.getEmail());
        assertEquals(userRespDto.getFirstName(), adminUser.getFirstName());
        assertEquals(userRespDto.getLastName(), adminUser.getLastName());
    }
}