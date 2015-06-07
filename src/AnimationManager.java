
import java.awt.EventQueue;

public class AnimationManager {
	static public double FPS = Constants.FPS;
	
	private MainCanvas canvas;
	private Thread animationThread;
	private boolean stopThread = false;
	private boolean pauseThread = false;
	
	public AnimationManager() {
		this.animationThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!stopThread) {
					if(AnimationManager.this.canvas != null) {
						try {
							AnimationManager.this.canvas.animateAllObjectNextFrame();
							EventQueue.invokeAndWait(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									AnimationManager.this.canvas.repaint();
								}
							});
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					while(AnimationManager.this.pauseThread); // pause thread 
					
					try {
						Thread.sleep((long)(1000 / FPS));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				stopThread = false;
				pauseThread = false;
			}
		});
	}
	
	public void registerCanvas(MainCanvas canvas) {
		this.canvas = canvas;
	}
	
	public void start() {
		if(pauseThread) {
			pauseThread = false;
		}
		else {
			this.animationThread.start();
		}
	}
	
	public void pause() {
		pauseThread = true;
	}
	
	public void stop() {
		pauseThread = false;
		stopThread = true;
	}
	
}
