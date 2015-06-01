import java.net.MalformedURLException;
import java.net.URL;


public class Resources {
	
	static public URL robotPath;
	static private String bundleString = "/Users/Luavis/";
	static private String robotPathString = "file://" + bundleString + Constants.ROBOT_OBJ_FILE;
	
	static void initResources() {
		try {
			robotPath = new URL(robotPathString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
}
