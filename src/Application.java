import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Application {
	
	private JFrame windowFrame;
	private AnimationManager manager = new AnimationManager();
	
	public Application(int windowWidth, int windowHeight, String windowTitle) {
		
		this.windowFrame = new JFrame(windowTitle);
		this.windowFrame.setBounds(30, 30, windowWidth, windowHeight);
		this.windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initApplication();
	}
	
	public JFrame getApplicationWindow() {
		return windowFrame;
	}
	
	public void startAnimation() {
		this.manager.start();
	}
	
	public void initApplication() {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		MainCanvas stage = new MainCanvas(config);
		manager.registerCanvas(stage);
		
		this.windowFrame.setLayout(new BorderLayout());
		this.windowFrame.add("Center", stage);
//		JButton button = new JButton("fuck hello");
//		
//		this.windowFrame.add(BorderLayout.PAGE_END, button);
	}
//  public void destroy() {
//    u.cleanup();
//  }
  
}