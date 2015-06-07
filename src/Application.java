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
}