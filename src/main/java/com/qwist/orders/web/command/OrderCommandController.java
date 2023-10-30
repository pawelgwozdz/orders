package com.qwist.orders.web.command;

import com.qwist.orders.dto.CreateOrderDto;
import com.qwist.orders.dto.CreateOrderRespDto;
import com.qwist.orders.dto.DeleteOrderRespDto;
import com.qwist.orders.service.command.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Api for transactions on orders
 */
@RestController()
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders")
//We could also use spring AOP for authorization instead of @PreAuthorize
public class OrderCommandController {

    private final UserCommandService userCommandService;

    /**
     * Api for creating orders
     * @param orderDto Order request body
     * @param principal Authentication request data
     * @return Response with order creation status
     */
    @Operation(
            description = "Endpoint that creates order for authorized user"
    )
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public CreateOrderRespDto createOrder(@Valid @RequestBody CreateOrderDto orderDto, Principal principal) {
        return userCommandService.createOrder(orderDto, principal);
    }

    /**
     * Api for deleting orders
     * @param orderId Order id that should be removed
     * @return Status of order deletion
     */
    @Operation(
            description = "Endpoint that removes chosen order by order ID"
    )
    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DeleteOrderRespDto deleteOrder(@PathVariable String orderId) {
        return userCommandService.deleteOrder(orderId);
    }
}
