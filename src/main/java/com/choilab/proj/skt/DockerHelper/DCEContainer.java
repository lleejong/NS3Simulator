package com.choilab.proj.skt.DockerHelper;

import java.util.ArrayList;

import com.choilab.proj.skt.ConfigUI;
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
		for (String log : logs)
			ConfigUI.log("  -" + log);
	}

	public String getHostname() {
		if (hostname.equals(""))
			hostname = DockerHelper.getHostname();
		return hostname;
	}
	
	public void doJob(NS3Data data){
		String cacheHost = CacheContainer.hostname;
		String args = cacheHost + data.toString();
		DockerHelper.dceTask(args,id);
	}

}
