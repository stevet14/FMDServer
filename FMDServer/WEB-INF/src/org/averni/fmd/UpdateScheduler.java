package org.averni.fmd;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class UpdateScheduler {

    public static void main(String[] args) throws Exception {
    	UpdateScheduler scheduler = new UpdateScheduler();
		scheduler.run();
    }

	public void run() throws Exception {
		Log log = LogFactory.getLog(UpdateScheduler.class);

		log.info("------- Initializing -------------------");

		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		log.info("------- Initialization Complete --------");

		log.info("------- Scheduling Jobs ----------------");

		// get a "nice round" time a few seconds in the future...
		// long ts = TriggerUtils.getNextGivenSecondDate(null, 15).getTime();
		long ts = TriggerUtils.getDateOf(0, 40, 23, 7, 3,
				2010).getTime();

		// job1 will only fire once at date/time "ts"
		JobDetail job = new JobDetail("job1", "group1", SymbolManager.class);
		SimpleTrigger trigger = new SimpleTrigger("trigger1", "group1", "job1",
				"group1", new Date(ts), null,
				SimpleTrigger.REPEAT_INDEFINITELY, 7L * 24L * 60L * 60L * 1000L);

		// schedule it to run!
		Date ft = sched.scheduleJob(job, trigger);
		log.info(job.getFullName() + " will run at: " + ft + " and repeat: "
				+ trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");
		System.out.println(job.getFullName() + " will run at: " + ft + " and repeat: "
				+ trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");

		log.info("------- Starting Scheduler ----------------");

		// All of the jobs have been added to the scheduler, but none of the
		// jobs
		// will run until the scheduler has been started
		sched.start();

		log.info("------- Started Scheduler -----------------");
	}
}
