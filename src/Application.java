import java.awt.Window;
import java.lang.reflect.Method;

import javax.swing.JFrame;

public class Application {
	
	static private Application singleton = null;
	
	private MainWindow windowFrame;
	private AnimationManager manager = new AnimationManager();
	
	
	synchronized static public Application getInstance() {
		if(singleton == null)
			singleton = new Application();
		
		return singleton;
	}
	
	public void initApplication(int windowWidth, int windowHeight, String windowTitle) {
		
		this.windowFrame = new MainWindow();
		this.windowFrame.setTitle(windowTitle);
		this.windowFrame.setBounds(30, 30, windowWidth, windowHeight);
		this.windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Application.getInstance().enableOSXFullscreen();
	}
	
	public JFrame getApplicationWindow() {
		return windowFrame;
	}
	
	public AnimationManager getAnimationManager() {
		return manager;
	}
	
	public void startAnimation() {
		this.manager.start();
	}
	
	public void enableOSXFullscreen() {
		try {
			/* 
			 * origin format for compatibility with windows
			 *	 com.apple.eawt.FullScreenUtilities.setWindowCanFullScreen(this.windowFrame, true);
			 *	 com.apple.eawt.Application.getApplication().requestToggleFullScreen(this.windowFrame);
			 */
			
			Class<?> fullscreenUtiles = Class.forName("com.apple.eawt.FullScreenUtilities");
			Class<?>[] fullscreenUtilsArgTypes = new Class[] { Window.class, boolean.class };
			Method setWindowCanFullScreen = fullscreenUtiles.getMethod("setWindowCanFullScreen", fullscreenUtilsArgTypes);
			setWindowCanFullScreen.invoke(fullscreenUtiles, this.windowFrame, true);
			
			Class<?> application = Class.forName("com.apple.eawt.Application");
			Class<?>[] applicationArgTypes = new Class[] { };
			Method getApplication = application.getMethod("getApplication", applicationArgTypes);
			Object applicationObject = getApplication.invoke(application);
			
			Class<?>[] requestToggleFullScreenArgTypes = new Class[] {Window.class };
			Method requestToggleFullScreen = application.getMethod("requestToggleFullScreen", requestToggleFullScreenArgTypes);
			requestToggleFullScreen.invoke(applicationObject, this.windowFrame);
	    }
	    catch (Exception e) {
	    	System.out.println("OS X full screen not support or Not OS X");
	    	e.printStackTrace();
	    }
	}
}