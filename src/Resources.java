import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class Resources {
	
	static private String bundleString = System.getProperty("user.dir");
	static public String robotPath = bundleString + File.separator + Constants.ROBOT_OBJ_FILE;
//	static private String robotPathString = "file://" + bundleString + Constants.ROBOT_OBJ_FILE;
	
	static void initResources() {
		System.out.println(robotPath);
		return;
	}
	
}
