package config;

public class ConfigSite {

	private static String url;
	
	
	public ConfigSite(String url) {
		super();
		ConfigSite.url = url;
	}
	
	public ConfigSite() {
		super();
	}

	
	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ConfigSite.url = url;
	}


}
