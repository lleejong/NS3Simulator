package proj.skt.Configure;

public class Configure {
	// TODO: Configure 상수들

	static private boolean cache;
	static private int type;
	static private int containers;
	static private int port;
	
	public static final String IMAGE_TAG_CACHE = "skt/ns3-dce-cache";
	public static final String IMAGE_TAG_DCE = "skt/ns3-dce";
	
	public static final String CONTAINER_TAG_CACHE = "dce-cache";
	public static final String CONTAINER_TAG_DCE_PREFIX = "dce-client";
	public static final int MAX_DCE_CONTAINER = 20;

	public static boolean isCache() {
		return cache;
	}

	public static void setCache(boolean cache) {
		Configure.cache = cache;
	}

	public static int getType() {
		return type;
	}
	public static int getPort(){
		return port;
	}

	public static void setType(int type) {
		Configure.type = type;
	}

	public static int getContainers() {
		return containers;
	}

	public static void setContainers(int containers) {
		Configure.containers = containers;
	}
	public static void setPort(int port) {
		Configure.port = port;
	}
}
