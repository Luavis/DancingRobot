
import java.io.File;


public class Resources {
	
	static private String bundleString = System.getProperty("user.dir");
	static public String evaRobotPath = bundleString + File.separator + Constants.EVA_ROBOT_OBJ_FILE;
	static public String androidRobotPath = bundleString + File.separator + Constants.ANDROID_ROBOT_OBJ_FILE;
	
//	static private String robotPathString = "file://" + bundleString + Constants.ROBOT_OBJ_FILE;
	
	static void initResources() {
		System.out.println(evaRobotPath);
		return;
	}
	
}
