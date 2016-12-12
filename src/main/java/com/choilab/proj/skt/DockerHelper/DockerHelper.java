package com.choilab.proj.skt.DockerHelper;

public class DockerHelper {
	
	//docker run -i -t -d --name dce_cache skt/ns3-dce-cache
	//docker run -i -t -d --name dce_client1 skt/ns3-dce
	
	//docker run -i -t -d --name dce_client2 skt/ns3-dce:1.4
	//hostname -i
	
	public static void initCache(){
		Docker.Run(Docker.CACHE_CONTAINER);
		String command = "docker run -i -t -d --name dce-cache skt/ns3-dce-cache";
		
	}
	
	public static void initDCE(){
		
	}
	
	public static class Docker{
		public static String CACHE_HOST;
		public static String CACHE_CONTAINER = "skt/ns3-dce-cache";
		public static String DCE_CONTAINER = "skt/ns3-dce-clinet";
		public static String DCE_CONTAINER_PREFIX = "ns3-dce-clinet";
		
		public static void Run(String args){
			String prefix = "docker run -i -t -d " + args;
			
		}
		public static void Start(){
			String prefix = "docker restart ";
		}
		public static void Stop(){
			String prefix= "docker stop ";
		}
		
		public static void Exec(){
			
		}
	}
}
