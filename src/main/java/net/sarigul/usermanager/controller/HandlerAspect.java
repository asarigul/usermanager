package net.sarigul.usermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.controller.errorhandler.ErrorHandlerFactory;
import net.sarigul.usermanager.core.Application;
import net.sarigul.usermanager.core.ApplicationException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

@Aspect
public class HandlerAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(HandlerAspect.class);
	 
	@Pointcut("within(net.sarigul.usermanager.controller.*)") 
	public void classPointcut() { }
	 
	@Pointcut("execution(* handleRequest(..))") 
	public void methodPointcut() { }
	
	
	@Around("classPointcut() && methodPointcut() && args(request,response)")
	public ModelAndView _handleRequest(ProceedingJoinPoint joinPoint, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Object target = joinPoint.getTarget();
		
		if(! (target instanceof Controller)) {
			throw new UnsupportedOperationException("unsupported controller: " + target.getClass());
		}
		
		try {
			if(Application.state().isDown()) {
				throw Application.state().cause();
			}
			
			return (ModelAndView) joinPoint.proceed();
		} catch(Throwable cause) {
			Controller controller = (Controller) target;
			LOGGER.debug("applying aspect to " + controller.getClass().getSimpleName());
			
			return ErrorHandlerFactory.get(cause, controller).handle(response); 
		}
	}
}
