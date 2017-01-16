package proj.skt.Job;

import java.util.ArrayList;

import proj.skt.Configure.Configure;
import proj.skt.DockerHelper.DCEContainer;

public class JobScheduler {

	private static final int DEFAULT_QUEUE_SIZE = 100;
	private JobPool pool;
	public JobScheduler(ArrayList<DCEContainer> dceContainers){
		pool = new JobPool(Configure.getContainers(),DEFAULT_QUEUE_SIZE, dceContainers);
	}
	
	public void newJob(Job job) throws Exception{
		pool.execute(job);
	}
}
