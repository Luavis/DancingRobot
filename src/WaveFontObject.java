
import java.io.FileNotFoundException;

import javax.media.j3d.Group;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;


public class WaveFontObject { // .obj file object in 3d
	
	protected String filePath;
	protected SmartBranchGroup branch;
	protected Scene scene;
	
	public WaveFontObject(String filePath) {
		this.filePath = filePath;
		scene = this.createScene();
	    branch = new SmartBranchGroup(scene.getSceneGroup());
	}
	
	protected Scene createScene() {
		int flags = ObjectFile.RESIZE;
		
		ObjectFile f = new ObjectFile(flags, 0);
	    Scene s = null;
	    
	    try {
	      s = f.load(filePath);
	    } catch (FileNotFoundException e) {
	      System.err.println(e);
	      System.exit(1);
	    } catch (ParsingErrorException e) {
	      System.err.println(e);
	      System.exit(1);
	    } catch (IncorrectFormatException e) {
	      System.err.println(e);
	      System.exit(1);
	    }
	    
	    return s;
	}
	
	public Group getSuperGroup() { 
		return branch.getSuperGroup();
	}
	
	public Group getBranchGroup() {
		return branch.getWrapGroup();
	}
}
