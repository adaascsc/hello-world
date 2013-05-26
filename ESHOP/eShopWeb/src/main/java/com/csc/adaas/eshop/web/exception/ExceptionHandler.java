package com.csc.adaas.eshop.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.csc.adaas.eshop.server.common.JspNames;

/**
 * This class will handle all Exceptions upper level to lower level
 * 
 * @author CSC {@link SimpleMappingExceptionResolver}
 */
public class ExceptionHandler extends SimpleMappingExceptionResolver {
	/** Logger */
	private Logger log = Logger.getLogger(ExceptionHandler.class.getName());

	/**
	 * Error Reference #
	 */
	private static final String ERROR_REFERENCE_NUMBER_MSG = "Error-Ref #: ";
	
	/**
	 * Key to use to specify the error code in the HttpServletRequest
	 */
	private static final String ERROR_REFERENCE_NUMBER = "errorRefNumber";
	
	/**
	 * This method overrides spring callback method doResolveException
	 * 
	 * @param {@HttpServletRequest}
	 * @param {HttpServletResponse}
	 * @param {@Object} Spring Controller
	 * @param {@Exception} Exception
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		int referenceHash = RandomUtils.nextInt(100000);
		StringBuilder builder = new StringBuilder();
		builder.append(ERROR_REFERENCE_NUMBER_MSG);
		builder.append(String.valueOf(referenceHash));

		request.setAttribute(ERROR_REFERENCE_NUMBER, String.valueOf(referenceHash));
		
		if (ex instanceof AccessDeniedException) {
			log.info("Access Denied Exception, check whether user has privileages" + builder.toString() , ex);
			request.setAttribute("instructions", "accessDenied");
			return (new ModelAndView(JspNames.ACCESS_DENIED.getView()));
		}
		
		if (ex instanceof CannotCreateTransactionException) {
			log.info("Database stale connection exception, check database connection" + builder.toString() , ex);
			request.setAttribute("instructions", "staleConnection");
			return (getErrorModelAndView());
		}
		
		if (ex instanceof DataAccessException) {
			log.info("Data Access exception cannot perform SQL query" + builder.toString() , ex);
			request.setAttribute("instructions", "dataAccessException");
			return (getErrorModelAndView());
		}
		if (ex instanceof RuntimeException) {
			log.info("Runtime Exception, check whats happening", ex);
			request.setAttribute("instructions", "runtimeException");
			return (getErrorModelAndView());
		}

		return getErrorModelAndView();
	}

	/**
	 * This method will return a Common Error ModelAndView Object
	 * 
	 * @return {@ModelAndView}
	 */
	private ModelAndView getErrorModelAndView() {
		ModelAndView errView = new ModelAndView(JspNames.ERROR_PAGE.getView());
		return errView;
	}
}