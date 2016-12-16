package com.choilab.proj.skt.DockerHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.choilab.proj.skt.Configure.Configure;

public class DockerHelper {

	// docker run -i -t -d --name dce_cache skt/ns3-dce-cache
	// docker run -i -t -d --name dce_client1 skt/ns3-dce

	// docker run -i -t -d --name dce_client2 skt/ns3-dce:1.4
	// hostname -i

	public static ArrayList<String> executionResult;
	
	public static ArrayList<String> initCacheContainer() {
		String command = "docker run -i -t -d --name "+ Configure.CONTAINER_TAG_CACHE + " " + Configure.IMAGE_TAG_CACHE;
		ArrayList<String> result = exec(command);
		command = "docker exec -i -t "+ Configure.CONTAINER_TAG_CACHE +" /bin/bash service mysql start";
		exec(command);
		//docker exec -i -t ns3-dce-cache bash -c "mysql -uroot < /NS3CacheServer/ns3_structure.sql"
		command = "docker exec -i -t " + Configure.CONTAINER_TAG_CACHE + " /bin/bash -c \"mysql -uroot < /NS3CacheServer/ns3_structure.sql\"";
		exec(command);
		return result;
	}
	
	public static void dceTask(String args, int containerID) {
		String command = "docker exec -i -t "+ (Configure.CONTAINER_TAG_DCE_PREFIX + containerID) + " /bin/bash -c \"/NS3Client/run.sh "+ args +"\"";
		//ArrayList<String> result = exec(command);
		exec(command);
	}
	
	public static ArrayList<String> initDCEContainer(int id) {
		String command = "docker run -i -t -d --name "+ (Configure.CONTAINER_TAG_DCE_PREFIX+id) + " " + Configure.IMAGE_TAG_DCE;
		ArrayList<String> result = exec(command);
		return result;
	}
	
	public static ArrayList<String> startCacheContainer() {
		String command = "docker stop "+ Configure.CONTAINER_TAG_CACHE;
		exec(command);
		command = "docker start " + Configure.CONTAINER_TAG_CACHE;
		ArrayList<String> result = exec(command);
		command = "docker exec -i -t -d "+ Configure.CONTAINER_TAG_CACHE +" /bin/bash service mysql start";
		exec(command);
		
		return result;
	}
	public static String getHostname(){
		String command = "docker exec -i -t " + Configure.CONTAINER_TAG_CACHE +" hostname -i";
		ArrayList<String> result = exec(command);
		return result.get(0);
	}
	public static void dceInit(){
		String command = "docker rm " + Configure.CONTAINER_TAG_DCE_PREFIX;
		for(int i = 1; i < Configure.MAX_DCE_CONTAINER; i++){
			exec(command + i);
		}
	}
	public static boolean dockerImageCheck(){
		String command = "docker images";
		ArrayList<String> result = exec(command);
		boolean cacheImg = false;
		boolean dceImg = false;
		
		for(String log : result){
			if(log.contains(Configure.IMAGE_TAG_CACHE))
				cacheImg = true;
			else if(log.contains(Configure.IMAGE_TAG_DCE))
				dceImg = true;
		}
		
		return cacheImg & dceImg;
	}
	
	
	

	private static ArrayList<String> exec(String command) {
		try {
			System.out.println(command);
			Process process = Runtime.getRuntime().exec(command);
			final InputStream is = process.getInputStream();
			executionResult = new ArrayList<String>();
			
			new Thread(new Runnable() {
				public void run() {
					try {
						
						String line;
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						while ((line = reader.readLine()) != null) {
							executionResult.add(line);
							System.out.println(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (is != null) {
							try {
								is.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}).start();
			process.waitFor();
		
			return executionResult;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<String> getContainerList() {
		String command = "docker ps -a";
		ArrayList<String> result = exec(command);
		return result;
	}
}
