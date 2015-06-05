import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class Resources {
	
	static private String bundleString = System.getProperty("user.dir");
	static public String evaRobotPath = bundleString + File.separator + Constants.EVA_ROBOT_OBJ_FILE;
	static public String androidRobotPath = bundleString + File.separator + Constants.ANDROID_ROBOT_OBJ_FILE;
	
	static public String evaButtonPath = bundleString + File.separator + Constants.EVA_BUTTON_IMAGE;
	static public String androidButtonPath = bundleString + File.separator + Constants.ANDROID_BUTTON_IMAGE;
	static public String playDanceButtonPath = bundleString + File.separator + Constants.PLAY_DANCE_BUTTON_IMAGE;
	static public String stopDanceButtonPath = bundleString + File.separator + Constants.STOP_DANCE_BUTTON_IMAGE;
	
	static void initResources() {
		System.out.println(evaRobotPath);
		return;
	}
	
}
