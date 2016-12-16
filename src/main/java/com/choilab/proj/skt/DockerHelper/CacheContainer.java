package com.choilab.proj.skt.DockerHelper;

import java.util.ArrayList;

import com.choilab.proj.skt.ConfigUI;
import com.choilab.proj.skt.Configure.Configure;

public class CacheContainer {
	
	public static String hostname = "";
	
	public CacheContainer() {
		init();
	}

	private void init() {
		// 캐시 컨테이너가 생성되어있는지 확인
		ArrayList<String> list = DockerHelper.getContainerList();
		boolean isExist = false;
		for (String str : list) {
			if (str.contains(Configure.CONTAINER_TAG_CACHE)) {
				isExist = true;
				break;
			}
		}

		if (isExist) {
			ArrayList<String> logs = DockerHelper.startCacheContainer();
			for (String log : logs)
				ConfigUI.log("  -" + log);
		} else {
			ConfigUI.log("Initiate Cache Container..");
			ArrayList<String> logs = DockerHelper.initCacheContainer();
			for (String log : logs)
				ConfigUI.log("  -" + log);
		}
	}

	public String getHostname() {
		if (CacheContainer.hostname.equals(""))
			CacheContainer.hostname = DockerHelper.getHostname();
		return CacheContainer.hostname;
	}

}
