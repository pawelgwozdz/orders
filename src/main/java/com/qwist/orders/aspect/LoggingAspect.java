package com.qwist.orders.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Takes care of logging when annotation LogError is applied
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.qwist.orders.annotations.LogError)")
    public void logRequestAction() {
    }

    @Before(value = "logRequestAction()")
    public void logRequest(JoinPoint joinPoint){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        logger.error("Request by user: {}. Method: {}. Request parameters: {}",
                authentication != null ? authentication.getPrincipal() : "Auth is null",
                methodSignature.getName(),
                getParamsAsString(joinPoint));
    }

    private String getParamsAsString(JoinPoint joinPoint){

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = codeSignature.getParameterNames()[i];
            builder.append(parameterName);
            builder.append(": ");
            builder.append(joinPoint.getArgs()[i].toString());
            builder.append(", ");
        }

        return builder.toString();
    }
}
