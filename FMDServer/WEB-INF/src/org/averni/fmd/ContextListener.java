package org.averni.fmd;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;

public class ContextListener implements ServletContextListener {

	  private ServletContext context = null;
	  private Log log = LogFactory.getLog(ContextListener.class);
	  private UpdateScheduler scheduler = new UpdateScheduler();

	  public ContextListener() {}

	  //This method is invoked when the Web Application
	  //is ready to service requests
	  public void contextInitialized(ServletContextEvent event)
	  {
	    this.context = event.getServletContext();
		try {
			scheduler.run();
			log.info("Scheduler running...");
		} catch (Exception e) {
			log.error("Scheduler failed to run...");
			e.printStackTrace();
		}
	  }

	  //This method is invoked when the Web Application
	  //has been removed and is no longer able to accept
	  //requests
	  public void contextDestroyed(ServletContextEvent event)
	  {
		  try {
			scheduler.shutdown();
			log.info("Scheduler shutdown...");
		} catch (SchedulerException e) {
			log.error("Scheduler failed to shutdown...");
			e.printStackTrace();
		}
		  this.context = null;

	  }


}
