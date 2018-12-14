package com.udemy.backendninja;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: Auto-generated Javadoc
/**
 * The Class BackendninjaApplication.
 */
@SpringBootApplication
@EnableScheduling // permite las tareas automáticas ( video 072)
public class BackendninjaApplication {
	
	/** The Constant LOG. */
	public static final Log LOG = LogFactory.getLog(BackendninjaApplication.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendninjaApplication.class, args);
		LOG.info(" -----------------------------------------------------------------------------------------------");
		LOG.info(" ------------------------------------------- MAIN ----------------------------------------------");
		LOG.info(" -----------------------------------------------------------------------------------------------");
	}
}
