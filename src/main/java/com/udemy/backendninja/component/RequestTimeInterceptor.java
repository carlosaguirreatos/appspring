package com.udemy.backendninja.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.udemy.backendninja.respository.LogRepository;


// TODO: Auto-generated Javadoc
// al marcar con @component la clase, Spring crea el BEAN y se lo guarda para cuando arranque la aplicación
// pero para que funcione hay que "darlo de alta en el WebMvcConfituration.java del paquete Configuration

/**
 * The Class RequestTimeInterceptor.
 */
@Component("requestTimeInterceptor")

public class RequestTimeInterceptor extends HandlerInterceptorAdapter{
	
	
	/** The log repository. */
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class); 

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) // SE EJECUTA ANTES DE LA PETICION AL MÉTODO DEL CONTROLADOR VER VIDEO 033
			throws Exception {
		// TODO Auto-generated method stub
		// return super.preHandle(request, response, handler);
		request.setAttribute("startTime", System.currentTimeMillis()); // guarda en startTime el tiempo actual en milisegundos
		return true;		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) // SE EJECUTA ANTES DE ESCUPIR LA VISTA DENTRO DEL MÉTODO DEL CONTROLADOR
			throws Exception {
		// TODO Auto-generated method stub
		//super.afterCompletion(request, response, handler, ex);
		long startTime = (long) request.getAttribute("startTime");
		
		//video 068 (PARA METER EN LA BBDD TODOS LOS LOGS
		String url = request.getRequestURL().toString();		
		LOG.info("URL to : '" + url + "' in '" + (System.currentTimeMillis() - startTime ) + "' ms");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username="";
		
		if (auth != null && auth.isAuthenticated()) {
			username = auth.getName();
		}
		// nos creamos una entidad log (pone toda la ruta para que no choque con el org.apache.commons.logging.Log;
		// com.udemy.backendninja.entity.Log log = new com.udemy.backendninja.entity.Log(new Date(), auth.getDetails().toString(), username, url);
		logRepository.save(new com.udemy.backendninja.entity.Log(new Date(), auth.getDetails().toString(), username, url));
		
		//LOG.info("URL to : '" + request.getRequestURL().toString() + "' in '" + (System.currentTimeMillis() - startTime ) + "' ms"); antes de video 068 (SI QUEREMOS QUE SALGA EN CONSOLA LO DESCOMENTAMOS)
		LOG.info("-----------------------------------------------------------------------------------------------------------------------------------");
	}
}
