package com.qwist.orders.web.handlers;

import com.qwist.orders.annotations.LogError;
import com.qwist.orders.dto.ErrorResponseDto;
import com.qwist.orders.exceptions.OrderNotFoundException;
import com.qwist.orders.exceptions.UserNotFoundException;
import com.qwist.orders.exceptions.WrongGetOrdersRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller advice for orders
 */
@RestControllerAdvice
public class OrdersControllerHandler {

    /**
     * handles UserNotFoundException
     * @param ex UserNotFoundException
     * @return Response entity with title, name and exception reason
     */
    @LogError
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> handle(UserNotFoundException ex) {
        ErrorResponseDto resp = ErrorResponseDto.builder()
                .title(ex.getExceptionMessage().getTitle())
                .name(ex.getExceptionMessage().getName())
                .reason(ex.getExceptionMessage().getReason())
                .build();

        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    /**
     * handles OrderNotFoundException
     * @param ex OrderNotFoundException
     * @return Response entity with title, name and exception reason
     */
    @LogError
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> handle(OrderNotFoundException ex) {
        ErrorResponseDto resp = ErrorResponseDto.builder()
                .title(ex.getExceptionMessage().getTitle())
                .name(ex.getExceptionMessage().getName())
                .reason(ex.getExceptionMessage().getReason())
                .build();

        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    /**
     * handles AccessDeniedException
     * @param ex AccessDeniedException
     * @return Response entity with title, name and exception reason
     */
    @LogError
    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponseDto> handle(AccessDeniedException ex) {
        ErrorResponseDto resp = ErrorResponseDto.builder()
                .title(ex.getMessage())
                .name(ex.getMessage())
                .reason("Access was denied for chosen user")
                .build();

        return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
    }

    /**
     * handles MethodArgumentNotValidException, invoked when request body is wrong
     * @param ex MethodArgumentNotValidException
     * @return Response entity with title, name and exception reason
     */
    @LogError
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handle(MethodArgumentNotValidException ex) {
        ErrorResponseDto resp = ErrorResponseDto.builder()
                .title("Bad Request")
                .name("Bad Request")
                .reason("Bad Request, check if all fields are correct")
                .build();

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    /**
     * handles WrongGetOrdersRequestException, invoked when request body is wrong
     * @param ex WrongGetOrdersRequestException
     * @return Response entity with title, name and exception reason
     */
    @LogError
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handle(WrongGetOrdersRequestException ex) {
        ErrorResponseDto resp = ErrorResponseDto.builder()
                .title(ex.getExceptionMessage().getTitle())
                .name(ex.getExceptionMessage().getName())
                .reason(ex.getExceptionMessage().getReason())
                .build();

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    /**
     * handles Exceptions
     * @param ex Exception
     * @return Response entity with title, name and exception reason
     */
    @LogError
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handle(Exception ex) {
        //probably time of error would be useful in ErrorResponseDto.class
        ErrorResponseDto resp = ErrorResponseDto.builder()
                .title("Unknown Error")
                .name("Unknown Error")
                .reason("Unknown error occurred, please contact system administrator")
                //.reason(ex.getMessage())
                .build();

        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
