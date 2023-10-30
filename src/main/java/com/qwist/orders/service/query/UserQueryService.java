package com.qwist.orders.service.query;

import com.qwist.orders.dto.UserRespDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    List<UserRespDto> getOrders(Optional<String> user, Authentication authentication);
}
