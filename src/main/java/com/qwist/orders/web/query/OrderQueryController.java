package com.qwist.orders.web.query;

import com.qwist.orders.dto.UserRespDto;
import com.qwist.orders.service.query.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Api for querying orders
 */
@RestController()
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders")
//We could also use spring AOP for authorization instead of @PreAuthorize
public class OrderQueryController {

    private final UserQueryService userQueryService;

    /**
     * Api for getting orders of users
     * @param user Username, can be null
     * @param authentication Authentication request data
     * @return Response containing users with their orders
     */
    //There was also an option to create interface for OrdersController and add @Operation in interface methods, It would provide more readability
    //We could also add here ResponseEntity, but I think It was not necessary for this kind of project
    @Operation(
            description = "Endpoint that returns order for authorized user. " +
                    "For user with role Customer it returns orders of authorized user. " +
                    "For user with role Admin it returns all orders. " +
                    "For user with role admin and when user login path variable is specified it returns only orders of chosen user"
    )
    @GetMapping(value = { "/{user}", ""})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    /*Usually I would do here 2 endpoints for role Admin and for role Customer, or even 3 with one more for admin with path variable for user
    but in task description was that there should be only one endpoint for listing orders*/
    public List<UserRespDto> getOrders(@PathVariable Optional<String> user, Authentication authentication) {
        return userQueryService.getOrders(user, authentication);
    }
}
