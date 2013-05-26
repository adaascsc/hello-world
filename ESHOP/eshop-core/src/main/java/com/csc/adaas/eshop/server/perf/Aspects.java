/**
 * 
 */
package com.csc.adaas.eshop.server.perf;

import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StopWatch;

import com.csc.adaas.eshop.server.common.Util;

/**
 * 
 * @author CSC
 * 
 */

@Aspect
public class Aspects {

	private Logger log = Logger.getLogger(Aspects.class.getName());

	@Pointcut("execution(* com.csc.ace.sqlfire.server.services..*.*(..))")
	public void profiling() {

	}

	@Around("profiling()")
	public Object doProfiling(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		log.info("Calling Service class -->" + pjp.getTarget().getClass().getName());
		StopWatch sw = new StopWatch();
		String clazz = pjp.getTarget().getClass().getName();
		sw.start(clazz);
		Object returnValue = pjp.proceed();
		sw.stop();
		long endTime = System.currentTimeMillis();
		log.info("Executed Service method in class: " + clazz + " in " + sw.getTotalTimeSeconds() + " seconds");
		log.info("Executed Service and DAO methods in: " + Util.getTimeDiff(startTime, endTime, Calendar.SECOND)
				+" seconds");
		return returnValue;
	}

	@Pointcut("execution(* com.csc.ace.sqlfire.server.dao..*.*(..))")
	public void daoProfiling() {

	}

	@Around("daoProfiling()")
	public Object doDaoProfiling(ProceedingJoinPoint pjp) throws Throwable {
		log.info("Calling DAO class -->" + pjp.getTarget().getClass().getName());
		StopWatch sw = new StopWatch();
		String clazz = pjp.getTarget().getClass().getName();
		sw.start(clazz);
		Object returnValue = pjp.proceed();
		sw.stop();
		log.info("Executed DAO method in class: " + clazz + " in " + sw.getTotalTimeSeconds() + " seconds");
		return returnValue;
	}

}
