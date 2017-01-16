package proj.skt.DockerHelper;

import java.util.ArrayList;

import proj.skt.ConfigUI;
import proj.skt.Configure.Configure;
import proj.skt.Job.NS3Data;

public class DCEContainer {
	private static int dceID = 1;

	private int id;

	private String hostname = "";

	public DCEContainer() {
		this.id = dceID++;
		init();
	}

	private void init() {
		//DockerHelper.initDCEContainer(id);
	}

	public String getHostname() {
		if (hostname.equals(""))
			hostname = DockerHelper.getHostname();
		return hostname;
	}

	public void doJob(NS3Data data) {
		String cacheHost = CacheContainer.hostname;
		String args = cacheHost + " " + Configure.getPort() + " " + data.toString();
		System.out.println("[Container " + id + "]" + " Job assigned--" + data.toStringWithTagName());
		log("Job assigned--" + data.toStringWithTagName());
		// ConfigUI.log("[Container "+ id +"]" + " Job assigned--" +
		// data.toStringWithTagName());
		long startTime = System.currentTimeMillis();
		DockerHelper.dceTask(args, id, this);
		long endTime = System.currentTimeMillis();
		// ConfigUI.log("Elapsed Time : " + (endTime -startTime) /1000.0 + "
		// sec.");
		System.out.println("[Container " + id + "]" + " Elapsed Time : " + (endTime - startTime) / 1000.0 + " sec.");
		log("Elapsed Time : " + (endTime - startTime) / 1000.0 + " sec.");
	}

	public void log(String log) {
		ConfigUI.log("[Container " + id + "]" + " " + log);
	}

}
