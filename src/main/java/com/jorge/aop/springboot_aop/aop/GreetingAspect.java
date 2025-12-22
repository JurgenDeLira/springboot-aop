package com.jorge.aop.springboot_aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Before("GreetingServicePointCuts.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes: " + method + " con los argumentos " + args);
    }

    @After("GreetingServicePointCuts.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Después: " + method + " con los argumentos " + args);
    }

    @AfterReturning("GreetingServicePointCuts.greetingLoggerPointCut()")
    public void loggerAfterReturning(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Después de retornar: " + method + " con los argumentos " + args);
    }

    @AfterThrowing("GreetingServicePointCuts.greetingLoggerPointCut()")
    public void loggerAfterThrowing(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Después de lanzar la excepción: " + method + " con los argumentos " + args);
    }

    @Around("GreetingServicePointCuts.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;
        try {
            logger.info("El método " + method + "() con los parámetros " + args);
            result = joinPoint.proceed();
            logger.info("El método " + method + "() retorna el resultado: " + result);
            return result;
        }catch (Throwable e){
            logger.error("Error en la llamada del método " + method + "()");
            throw e;
        }

    }
}
