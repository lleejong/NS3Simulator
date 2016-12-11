package com.choilab.proj.skt.Job;

public class JobHandler implements Runnable{
	private int id;
	private JobQueue queue;
	private volatile boolean running = true;
	
	public JobHandler(int id, JobQueue queue){
		this.id = id;
		this.queue = queue;
		
		//docker container
	}
	
	public void run(){
		while(running){
			try{
				Thread.sleep(10);
				Job job = queue.dequeue();
				job.run();
				
			}catch(InterruptedException e ){
				stop();
			}
		}
	}
	
	public void stop(){
		running = false;
	}

}
