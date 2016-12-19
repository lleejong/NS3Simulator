package com.choilab.proj.skt.Job;

import com.choilab.proj.skt.DockerHelper.DCEContainer;

public class JobHandler implements Runnable{
	private int id;
	private JobQueue queue;
	private volatile boolean running = true;
	private DCEContainer container;
	
	public JobHandler(int id, JobQueue queue, DCEContainer container){
		this.id = id;
		this.queue = queue;
		this.container = container;
		
		//docker container
	}
	
	public void run(){
		while(running){
			try{
				Thread.sleep(10);
				Job job = queue.dequeue();
				container.doJob(job.getNS3Data());
			}catch(InterruptedException e ){
				System.out.println("" + id + " container stopped.");
				stop();
			}
		}
	}
	
	public void stop(){
		running = false;
	}

}
