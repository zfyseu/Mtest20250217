package org.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.param.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExceptionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);

    @Pointcut("execution(* org.example.controller.*.*(..))")
    public void exceptionPointcut() {}

    @Around("exceptionPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
            return Response.fail(e.getMessage());
        }
    }

}
