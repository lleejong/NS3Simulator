package com.choilab.proj.skt.Job;

import java.util.LinkedList;

public class JobQueue {

	private LinkedList<Job> queue = new LinkedList<Job>();
	private int MAX_QUEUE_SIZE = 10;
	
	public JobQueue(int MAX_QUEUE_SIZE){
		this.MAX_QUEUE_SIZE = MAX_QUEUE_SIZE;
	}
	

	public synchronized void enqueue(Job job) throws InterruptedException{
		while(queue.size() == MAX_QUEUE_SIZE){
			wait();
		}
		if(queue.size() == 0){
			notifyAll();
		}
		
		queue.add(job);
	}
	
	public synchronized Job dequeue() throws InterruptedException{
		while(queue.size() == 0){
			wait();
		}
		if(queue.size() == MAX_QUEUE_SIZE){
			notifyAll();
		}
		return queue.remove(0);
	}
}
