package com.choilab.proj.skt.DockerHelper;

import java.util.ArrayList;

import com.choilab.proj.skt.ConfigUI;
import com.choilab.proj.skt.Configure.Configure;
import com.choilab.proj.skt.Job.NS3Data;

public class DCEContainer {
	private static int dceID = 1;

	private int id;

	private String hostname = "";

	public DCEContainer() {
		this.id = dceID++;
		init();
	}

	private void init() {
		ConfigUI.log("Initiate DCE Container.. " + id);
		ArrayList<String> logs = DockerHelper.initDCEContainer(id);
		if (logs != null)
			for (String log : logs)
				ConfigUI.log("  -" + log);
	}

	public String getHostname() {
		if (hostname.equals(""))
			hostname = DockerHelper.getHostname();
		return hostname;
	}

	public void doJob(NS3Data data) {
		String cacheHost = CacheContainer.hostname;
		String args = cacheHost + " " + Configure.getPort() + " " +data.toString();
		long startTime = System.currentTimeMillis();
		DockerHelper.dceTask(args, id);
		long endTime = System.currentTimeMillis();
		ConfigUI.log("Elapsed Time : " + (endTime -startTime) /1000.0 + " sec.");
	}

}
