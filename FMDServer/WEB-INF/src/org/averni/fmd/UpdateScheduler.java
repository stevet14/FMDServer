package org.averni.fmd;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class UpdateScheduler {

	private Scheduler sched;
	
    public static void main(String[] args) throws Exception {
    	UpdateScheduler scheduler = new UpdateScheduler();
		scheduler.run();
    }

	public void run() throws Exception {
		Log log = LogFactory.getLog(UpdateScheduler.class);

		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		sched = sf.getScheduler();

		log.info("------- Scheduling Jobs ----------------");

		//Set scheduled date to be next Saturday.
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		
//		Date ts = TriggerUtils.getDateOf(0, 35, 9, cal.get(Calendar.DATE), cal.get(Calendar.MONTH)+1,
		Date ts = TriggerUtils.getDateOf(00, 00, 01, cal.get(Calendar.DATE), cal.get(Calendar.MONTH)+1,
				cal.get(Calendar.YEAR));

		// job1 will only fire once a week on Saturdays at 1am.
		JobDetail job = new JobDetail("job1", "group1", SymbolManager.class);
		SimpleTrigger trigger = new SimpleTrigger("trigger1", "group1", "job1",
				"group1", ts, null,
				//SimpleTrigger.REPEAT_INDEFINITELY, 7L * 24L * 60L * 60L * 1000L);
				SimpleTrigger.REPEAT_INDEFINITELY, 20L * 60L * 1000L);

		// schedule it to run!
		Date ft = sched.scheduleJob(job, trigger);
		log.info(job.getFullName() + " will run at: " + ft + " and repeat: "
				+ trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");

		sched.start();

		log.info("------- Started Scheduler -----------------");
	}
	
	public void shutdown() throws SchedulerException {
		sched.shutdown();
	}
}
