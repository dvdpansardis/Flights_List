package br.com.flightslist.util;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggerAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

	@Before(value = "execution(* br.com.flightslist.*.*(..))")
	public void logMethod(JoinPoint joinPoint) throws Throwable {

		LOGGER.info("[Enter method : " + joinPoint.getSignature().getName());
		LOGGER.info("Arguments : " + Arrays.toString(joinPoint.getArgs()) + "]");

		System.out.println("[Enter method : " + joinPoint.getSignature().getName());
		System.out.println("Arguments : " + Arrays.toString(joinPoint.getArgs()) + "]");

	}

	@AfterThrowing(pointcut = "execution(* br.com.flightslist.*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

		LOGGER.info("[Enter method : " + joinPoint.getSignature().getName());
		LOGGER.info("Exception : " + error + "]");

		System.out.println("[Enter method : " + joinPoint.getSignature().getName());
		System.out.println("Exception : " + error + "]");

	}
}
