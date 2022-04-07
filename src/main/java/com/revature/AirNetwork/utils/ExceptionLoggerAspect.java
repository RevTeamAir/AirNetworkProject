package com.revature.AirNetwork.utils;

import com.revature.AirNetwork.models.JsonResponse;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * This class is a Spring AOP Aspect that uses "@AfterThrowing" point cut expression to log every exception that occurs in our controller methods.
 */

@Component
@Aspect
public class ExceptionLoggerAspect {

    Logger logger = Logger.getLogger(ExceptionLoggerAspect.class);

    // USER
    @AfterThrowing(value = "execution(* *User(..))", throwing = "e")
    public void logUserExceptionThrown(JoinPoint joinPoint, Exception e){

        logger.warn("A user-related exception was thrown: ", e);
    }

    // SESSION
    @AfterThrowing(value = "execution(* *Session(..))", throwing = "e")
    public void logSessionExceptionThrown(JoinPoint joinPoint, Exception e){

        logger.warn("A session-related exception was thrown: ", e);
    }

    // POST
    @AfterThrowing(value = "execution(* *Post(..))", throwing = "e")
    public void logPostExceptionThrown(JoinPoint joinPoint, Exception e){

        logger.warn("A post-related exception was thrown: ", e);
    }

    // LIKE
    @AfterThrowing(value = "execution(* *Like(..))", throwing = "e")
    public void logLikeExceptionThrown(JoinPoint joinPoint, Exception e){

        logger.warn("A post-related exception was thrown: ", e);
    }
}
