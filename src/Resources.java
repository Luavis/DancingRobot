import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class Resources {
	
	static private String bundleString = System.getProperty("user.dir");
	
	static public String evaRobotPath = bundleString + File.separator + Constants.EVA_ROBOT_OBJ_FILE;
	
	static public String androidRobotPath = bundleString + File.separator + Constants.ANDROID_ROBOT_OBJ_FILE;
	
	static public String bgSoundPath = bundleString + File.separator + Constants.BG_SOUND_FILE;
	
	static public String evaButtonPath = bundleString + File.separator + Constants.EVA_BUTTON_IMAGE;
	
	static public String androidButtonPath = bundleString + File.separator + Constants.ANDROID_BUTTON_IMAGE;
	
	static public String playDanceButtonPath = bundleString + File.separator + Constants.PLAY_DANCE_BUTTON_IMAGE;
	
	static public String stopDanceButtonPath = bundleString + File.separator + Constants.STOP_DANCE_BUTTON_IMAGE;
	
	static public String playMusicButtonPath = bundleString + File.separator + Constants.MUSIC_PLAY_BUTTON_IMAGE;
	
	static public String stopMusicButtonPath = bundleString + File.separator + Constants.MUSIC_STOP_BUTTON_IMAGE;
	
	static public Music backgroundSound;
	
	static void initResources() {
		if(
				!(Resources.checkFileExist(evaButtonPath) &&
				Resources.checkFileExist(androidButtonPath) &&
				Resources.checkFileExist(androidRobotPath) &&
				Resources.checkFileExist(evaButtonPath) &&
				Resources.checkFileExist(evaRobotPath) &&
				Resources.checkFileExist(playDanceButtonPath) && 
				Resources.checkFileExist(stopDanceButtonPath)
				)
		) {
			throw(new IllegalStateException("Some resource files not found"));
		}
		else { // load Robots for caching objects
			backgroundSound = new Music(new File(bgSoundPath));
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					new Eva();
				}
			}).start();

			new Thread(new Runnable() {
				
				@Override
				public void run() {
					new Android();
				}
			}).start();
		}
		return;
	}
	
	static boolean checkFileExist(String path) {
		return new File(path).exists();
	}
	
}
