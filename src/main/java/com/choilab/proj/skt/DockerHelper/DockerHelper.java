package com.choilab.proj.skt.DockerHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.choilab.proj.skt.Configure.Configure;

public class DockerHelper {
	private static final int SLEEP_TIMER_SHORT = 200;
	private static final int SLEEP_TIMER_LONG = 500;

	// docker run -i -t -d --name dce_cache skt/ns3-dce-cache
	// docker run -i -t -d --name dce_client1 skt/ns3-dce

	// docker run -i -t -d --name dce_client2 skt/ns3-dce:1.4
	// hostname -i

	public static ArrayList<String> executionResult;

	public static ArrayList<String> initCacheContainer() {
		try {
			String command = "docker run -i -t -d -p " + Configure.getPort() + ":" + Configure.getPort() + " --name " + Configure.CONTAINER_TAG_CACHE + " " + Configure.IMAGE_TAG_CACHE;
			ArrayList<String> result = exec(command);

			Thread.sleep(SLEEP_TIMER_LONG);

			command = "docker exec -i " + Configure.CONTAINER_TAG_CACHE + " /bin/bash service mysql start";
			exec(command);

			Thread.sleep(SLEEP_TIMER_SHORT);

			return result;

		} catch (InterruptedException e) {
			// e.printStackTrace();
			ArrayList<String> list = new ArrayList<String>();
			list.add(e.getMessage());
			return list;
		}

	}

	public static ArrayList<String> startCacheContainer() {
		try {

			String command = "docker stop " + Configure.CONTAINER_TAG_CACHE;
			exec(command);
			Thread.sleep(SLEEP_TIMER_SHORT);
			command = "docker start " + Configure.CONTAINER_TAG_CACHE;
			ArrayList<String> result = exec(command);
			Thread.sleep(SLEEP_TIMER_SHORT);
			command = "docker exec -i " + Configure.CONTAINER_TAG_CACHE + " /bin/bash service mysql start";
			exec(command);

			Thread.sleep(SLEEP_TIMER_SHORT);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void cacheServerExecute() {
		try {
			// String command = "docker exec -i " +
			// (Configure.CONTAINER_TAG_CACHE) + " /bin/bash -c \"cd
			// /NS3CacheServer && git pull && mvn compile && mvn package\"";
			// exec(command);

			// Thread.sleep(SLEEP_TIMER_LONG * 2);

			// String command = "docker exec " + Configure.CONTAINER_TAG_CACHE +
			// " /bin/bash -c \"mysql -uroot <
			// /NS3CacheServer/ns3_structure.sql\"";
			String command = "mysql -uroot < /NS3CacheServer/ns3_structure.sql";
			String[] cmdArr = { "docker", "exec", Configure.CONTAINER_TAG_CACHE, "/bin/sh", "-c", command };
			// docker exec -i dce-cache /bin/bash -c "mysql -uroot <
			// /NS3CacheServer/ns3_structure.sql";
			exec(cmdArr);

			Thread.sleep(SLEEP_TIMER_LONG * 2);

			// command = "docker exec -d " + Configure.CONTAINER_TAG_CACHE
			// + " /bin/bash -c \"cd /NS3CacheServer && java -cp
			// ./target/NS3CacheServer-0.0.1-SNAPSHOT.jar:\"/root/.m2/repository/mysql/mysql-connector-java/5.1.38/mysql-connector-java-5.1.38.jar\"
			// com.choilab.proj.skt.App "
			// + Configure.getPort() + " " + Configure.isCache() + " " +
			// Configure.getType() + "\"";

			String[] cmdArr2 = { "docker", "exec", "-i", "-d", Configure.CONTAINER_TAG_CACHE, "/bin/bash", "-c", "cd", "/NS3CacheServer","&&","java", "-cp",
					"./target/NS3CacheServer-0.0.1-SNAPSHOT.jar:/root/.m2/repository/mysql/mysql-connector-java/5.1.38/mysql-connector-java-5.1.38.jar", "com.choilab.proj.skt.App",
					Configure.getPort() + "", Configure.isCache() + "", Configure.getType() + "" };
			// List<String> cmd = new ArrayList<String>();
			//
			// cmd.add("docker");
			// cmd.add("exec");
			// cmd.add("-i");
			// cmd.add("-t");
			// cmd.add("-d");
			// cmd.add(Configure.CONTAINER_TAG_CACHE);
			// cmd.add("/bin/bash");
			// cmd.add("-c");
			// cmd.add("java");
			// cmd.add("-cp");
			// cmd.add("./target/NS3CacheServer-0.0.1-SNAPSHOT.jar:/root/.m2/repository/mysql/mysql-connector-java/5.1.38/mysql-connector-java-5.1.38.jar");
			// cmd.add("com.choilab.proj.skt.App");
			// cmd.add(Configure.getPort() + "");
			// cmd.add(Configure.isCache() + "");
			// cmd.add(Configure.getType() + "");

			System.out.println(Configure.isCache() + "");
			exec(cmdArr2);
			Thread.sleep(SLEEP_TIMER_LONG);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void dceTask(String args, int containerID) {
		String command = "docker exec -i " + (Configure.CONTAINER_TAG_DCE_PREFIX + containerID)
				+ " /bin/bash -c \"cd /NS3Client &&  java -cp ./target/NS3Client-0.0.1-SNAPSHOT.jar com.choilab.proj.skt.App " + args + "\"";
		// ArrayList<String> result = exec(command);
		ArrayList<String> result = exec(command);
		for (String log : result) {
			System.out.println(log);
		}

	}

	public static ArrayList<String> initDCEContainer(int id) {
		try {
			String command = "docker run -i -t -d --name " + (Configure.CONTAINER_TAG_DCE_PREFIX + id) + " " + Configure.IMAGE_TAG_DCE;
			ArrayList<String> result = exec(command);
			Thread.sleep(SLEEP_TIMER_SHORT);
			command = "docker exec -i " + (Configure.CONTAINER_TAG_DCE_PREFIX + id) + " /bin/bash -c \"cd /NS3Client && git pull && mvn compile && mvn package\"";
			exec(command);
			Thread.sleep(SLEEP_TIMER_LONG * 2);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getHostname() {
		String command = "docker exec -i " + Configure.CONTAINER_TAG_CACHE + " hostname -i";
		ArrayList<String> result = exec(command);
		return result.get(0);
	}

	public static void dceInit() {
		try {
			String command = "docker stop " + Configure.CONTAINER_TAG_DCE_PREFIX;
			for (int i = 1; i <= Configure.getContainers(); i++) {
				exec(command + i);
				Thread.sleep(500);
			}
			command = "docker rm " + Configure.CONTAINER_TAG_DCE_PREFIX;
			for (int i = 1; i <= Configure.getContainers(); i++) {
				exec(command + i);
				Thread.sleep(500);
			}
		} catch (Exception e) {

		}
	}

	public static boolean dockerImageCheck() {
		String command = "docker images";
		ArrayList<String> result = exec(command);
		boolean cacheImg = false;
		boolean dceImg = false;

		for (String log : result) {
			if (log.contains(Configure.IMAGE_TAG_CACHE))
				cacheImg = true;
			else if (log.contains(Configure.IMAGE_TAG_DCE))
				dceImg = true;
		}

		return cacheImg & dceImg;
	}

	private static ArrayList<String> exec(String command) {
		try {
			System.out.println("----" + command);
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
			System.out.println(command);

			return executionResult;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static ArrayList<String> exec(String[] command) {
		try {
			System.out.println("----" + command.toString());
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
			System.out.println(command);

			return executionResult;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void exec(List<String> cmd) {
		try {

			ProcessBuilder pb = new ProcessBuilder(cmd);
			Process process = pb.start();
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<String> getContainerList() {
		String command = "docker ps -a";
		ArrayList<String> result = exec(command);
		return result;
	}
}
