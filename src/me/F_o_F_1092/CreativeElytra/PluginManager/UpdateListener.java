package me.F_o_F_1092.CreativeElytra.PluginManager;

public class UpdateListener {

	protected static boolean updateAvailable = false;
	protected static double updateDouble;
	protected static String updateString;
	protected static String versionURL;
	protected static String tag;
	
	public static void initializeUpdateListener(double updateDouble, String updateString, String versionURL, String tag) {
		UpdateListener.updateDouble = updateDouble;
		UpdateListener.updateString = updateString;
		UpdateListener.versionURL = versionURL;
		UpdateListener.tag = tag;
	}
	
	public static double getUpdateDoubleVersion() {
		return updateDouble;
	}
	
	public static String getUpdateStringVersion() {
		return updateString;
	}
	
	public static boolean isAnewUpdateAvailable() {
		return updateAvailable;
	}
}
