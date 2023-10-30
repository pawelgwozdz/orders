package com.qwist.orders.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private String title;
    private String name;
    private String reason;
}
