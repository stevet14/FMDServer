package org.averni.fmd;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.averni.fmd.SymbolLoader.Exchange;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SymbolManager implements Job {

    private static Log log = LogFactory.getLog(SymbolManager.class);

    /**
     * Empty constructor for job initilization
     */
    public SymbolManager() {
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     * 
     * @throws JobExecutionException
     *             if there is an exception while executing the job.
     */
    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        // This job simply prints out its job name and the
        // date and time that it is running
        String jobName = context==null?"":context.getJobDetail().getFullName();
        log.info("SymbolManager - job name: " + jobName + " executing at " + new Date());
		loadAndStoreSymbols();
    }

    public static void main(String[] args) throws Exception {
		SymbolManager mgr = new SymbolManager();
		mgr.execute(null);
    }

	private void loadAndStoreSymbols() {
		SymbolLoader sl = new SymbolLoader();

/*		System.out.println("\nExchange: " + Exchange.FINDEX + "\n");
		sl.loadSymbols(Exchange.FINDEX);
		System.out.println("\nExchange: " + Exchange.INDEX + "\n");
		sl.loadSymbols(Exchange.INDEX);
		System.out.println("\nExchange: " + Exchange.LSE + "\n");
		sl.loadSymbols(Exchange.LSE);
*/		System.out.println("\nExchange: " + Exchange.FOREX + "\n");
		sl.loadSymbols(Exchange.FOREX);
/*		System.out.println("\nExchange: " + Exchange.FUTURES + "\n");
		sl.loadSymbols(Exchange.FUTURES);
*/	}

}
