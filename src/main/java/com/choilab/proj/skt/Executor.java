package com.choilab.proj.skt;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.choilab.proj.skt.Configure.Configure;
import com.choilab.proj.skt.Configure.ConfigureManager;
import com.choilab.proj.skt.DockerHelper.CacheContainer;
import com.choilab.proj.skt.DockerHelper.DCEContainer;
import com.choilab.proj.skt.DockerHelper.DockerHelper;
import com.choilab.proj.skt.Job.Job;
import com.choilab.proj.skt.Job.JobConverterFromFile;
import com.choilab.proj.skt.Job.JobScheduler;

public class Executor {

	public static void run(){
		if(!DockerHelper.dockerImageCheck()){
			ConfigUI.log("Problem with docker images. Check your docker environments");
			return ;
		}
		
		// 3. Server side init
		// 3-1. Container run 한 후, Server conatainer ip 얻어오기
		// 3-2. mysql table
		CacheContainer cacheContainer = CacheContainer.getInstance();
		// 4. Client side init
		// 4-1. Thread pool 생성
		
		ArrayList<DCEContainer> dceContainers = new ArrayList<DCEContainer>();
		
		DockerHelper.dceInit();
		for(int i = 0; i < Configure.getContainers(); i++){
			new DCEContainer();
		}
		
		JobScheduler jobScheduler = new JobScheduler(dceContainers);
		
		// 4-2. Configure에 정의된 만큼 Container 생성
		// 4-3. Thread당 하나의 Container 매칭

		
		// 5. JobScheduler
		ArrayList<Job> jobList = JobConverterFromFile.readXML();
		for(Job newJob : jobList)
			try {
				jobScheduler.newJob(newJob);
			} catch (Exception e) {
				ConfigUI.log(e.getMessage());
			}
	}

	public static void main(String[] args) throws InterruptedException {
		// 1. configure xml 파싱을 통해 load
		ConfigureManager.readXML();

		// 2. UI를 통해 configure 수정
		
		
		
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				new ConfigUI();
//			}
//		});
		
		Executor.run();
		
		
	}
}
