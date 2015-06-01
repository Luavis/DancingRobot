import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;

public class Music {
	
	private BasicPlayer musicPlayer;
	private Thread musicThread;
	private boolean isEnded;
	
	public Music(File musicFile) {
		isEnded = false;
		
		musicPlayer = new BasicPlayer();
		try {
			musicPlayer.open(musicFile.toURI().toURL());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void play() {
		try {
			if(this.isEnded)
				throw(new Exception("Already started"));
			
			this.isEnded = false;
			musicThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Music.this.musicPlayer.play();
						Music.this.musicPlayer.run();
						Music.this.isEnded = true;
					}
					catch(Exception e) {
						e.printStackTrace();
					}
			}});
			
			musicThread.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		try {
			this.musicPlayer.pause();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			if(this.musicPlayer != null)
				this.musicPlayer.stop();
			this.musicPlayer = null;
			this.musicThread = null;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.stop();
		super.finalize();
	}
}
