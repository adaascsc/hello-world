/**
 * 
 */
package com.csc.adaas.eshop.server.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StopWatch;

import com.csc.adaas.eshop.security.vo.UserProfile;
import com.csc.adaas.eshop.server.annotations.AppCache;
import com.csc.adaas.eshop.server.annotations.AppCacheDestroy;
import com.csc.adaas.eshop.server.common.ApplicationException;
import com.csc.adaas.eshop.server.common.EShopConstants;


/**
 * 
 * @author CSC
 * 
 */

@Aspect
public class Aspects {

	private Logger log = Logger.getLogger(Aspects.class.getName());

	private static final String prefixBefore = "[USER ID:";
	private static final String prefixAfter = "].";

	
	@Pointcut("execution(public * com.csc..server.dao..*.*(..))")
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
		log.info("Executed DAO method in class: " + clazz + " in "
				+ sw.getTotalTimeSeconds() + " seconds");
		return returnValue;
	}

	@Pointcut("execution(public * com.csc..server.services..*.*(..))")
	public void getFromGemFireCache() {

	}

	

	/**
	 * 
	 * @param aClass
	 * @param methodName
	 * @return String
	 * @throws Exception
	 */
	private String getRegionName(final Class serviceClass,
			final String methodName) throws Exception {
		String regionName = null;
		Annotation cacheAnnot = null;

		// get the region name from either method level or class level
		// annotation of Service class
		// First check the method
		final Method[] methods = serviceClass.getDeclaredMethods();
		for (final Method method : methods) {
			if (StringUtils.equals(method.getName(), methodName)) {
				cacheAnnot = method.getAnnotation(AppCache.class);
				break;
			}
		}
		// If present at Method level cacheAnnot will not be null so skip
		// checking at class level
		// otherwise check at class to level whether AppCache is defined
		if (cacheAnnot == null)
			cacheAnnot = serviceClass.getAnnotation(AppCache.class);

		// If Annotation is not null get the region name
		if (cacheAnnot != null) {
			regionName = ((AppCache) cacheAnnot).region();
		}
		return regionName;
	}

	/**
	 * Create Cache Key in a multi tenant fashion Add the tenant id as part of
	 * cache key
	 * 
	 * @param pjp
	 * @return String
	 * @throws Exception
	 */
	private String getCacheKey(ProceedingJoinPoint pjp) throws Exception {

		String targetName = pjp.getTarget().getClass().getSimpleName();
		String methodName = pjp.getSignature().getName();
		Object[] arguments = pjp.getArgs();
		StringBuilder cacheKeyBuilder = new StringBuilder();

		// Add the tenant id as part of GemFire Cache
		cacheKeyBuilder.append(getEntryPrefixKey());

		// Add the method name to cache key
		cacheKeyBuilder.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				Object obj = arguments[i];
				// build the cache by getting the hashcode of the object
				cacheKeyBuilder.append(".").append(obj.hashCode());
			}// End of for
		}// End of if
		return cacheKeyBuilder.toString();
	}

	/**
	 * Get the multi tenant pre-fix for GemFire cache entry key
	 */
	private String getEntryPrefixKey() {
		String prefix = null;
		SecurityContext context = null;
		Authentication authentication = null;
		UserProfile user = null;

		// Build GemFire Region cache entry prefix add [Tenant ID:X].
		if (SecurityContextHolder.getContext() != null) {
			context = SecurityContextHolder.getContext();
			if (context.getAuthentication() != null) {
				authentication = context.getAuthentication();
				user = (UserProfile) authentication.getPrincipal();
				prefix = prefixBefore + user.getUserid().toString()
						+ prefixAfter;
			} else {
				throw new ApplicationException(
						" Authentication object is null in com.csc..server.interceptor.Aspects.getEntryPrefixKey method, "
								+ "user known");
			}
		} else {
			// If Security context is null throw exception
			throw new ApplicationException(
					"Security Context is null in com.csc..server.interceptor.Aspects.getEntryPrefixKey method, user known");
		}
		return (StringUtils.isEmpty(prefix) ? EShopConstants.BLANK : prefix);
	}

	/**
	 * 
	 * @param aClass
	 * @param methodName
	 * @throws Exception
	 */
	private String hasCacheDestroyAnnotation(final Class aClass,
			final String methodName) throws Exception {
		log.info("Enter hasCacheDestroyAnnotation method");
		String regionToDestroy = null;
		Annotation cacheAnnot = null;

		// Check whether the method has @AppCacheDestroy annotation
		// if so delete all entries from the region
		final Method[] methods = aClass.getDeclaredMethods();
		for (final Method method : methods) {
			if (StringUtils.equals(method.getName(), methodName)) {
				cacheAnnot = method.getAnnotation(AppCacheDestroy.class);
				break;
			}
		}
		// If Annotation is not null get the region name
		if (cacheAnnot != null) {
			regionToDestroy = ((AppCacheDestroy) cacheAnnot).region();
		}
		log.info("Exiting hasCacheDestroyAnnotation method");
		return regionToDestroy;
	}

	
}
