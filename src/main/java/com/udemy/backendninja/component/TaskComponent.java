package com.udemy.backendninja.component;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// video 072
@Component("taskComponent")
public class TaskComponent {
	
	private static final Log LOG = LogFactory.getLog(TaskComponent.class);
	
	@Scheduled(fixedDelay= 5000) // marcamos como tarea repetitiva a cada 5 segundos
	public void doTask() {
		//LOG.info("TIME IS: " + new Date());
	}

}
