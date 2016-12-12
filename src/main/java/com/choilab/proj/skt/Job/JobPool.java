package com.choilab.proj.skt.Job;

import java.util.ArrayList;
import java.util.List;

public class JobPool {

	private JobQueue queue;
	private List<JobHandler> handlerList = new ArrayList<JobHandler>();
	private volatile boolean running = true;
	
	public JobPool(int MAX_HANDLER_NUM, int MAX_QUEUE_SIZE){
		queue = new JobQueue(MAX_QUEUE_SIZE);
		for(int i = 0; i < MAX_HANDLER_NUM; i ++){
			handlerList.add(new JobHandler(i,queue));
		}
		
		for(JobHandler jh : handlerList){
			new Thread(jh).start();
		}
	}
	
	public synchronized void execute(Job item) throws Exception{
		if(!running){
			throw new Exception("Thread pool is not running");
		}
		queue.enqueue(item);
	}
	
	public synchronized void stop(){
		running = false;
		for(JobHandler jh : handlerList){
			jh.stop();
		}
	}
}
