package com.udemy.backendninja.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import com.udemy.backendninja.component.Autevaluacion36TimeInterceptor;
import com.udemy.backendninja.component.RequestTimeInterceptor;

// TODO: Auto-generated Javadoc
/**
 * The Class WebMvcConfiguration.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{ // video 033
	
	/** The request time interceptor. */
 @Autowired
	@Qualifier("requestTimeInterceptor")
	private RequestTimeInterceptor requestTimeInterceptor;
	
	/*@Autowired
	@Qualifier("autevaluacion36TimeInterceptor")
	private Autevaluacion36TimeInterceptor autevaluacion36TimeInterceptor;*/
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {		
		
		registry.addInterceptor(requestTimeInterceptor);
		//registry.addInterceptor(autevaluacion36TimeInterceptor);		
	}
}
