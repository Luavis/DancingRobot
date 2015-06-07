import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;


abstract public class WaveFontObject { // .obj file object in 3d
	protected String filePath;
	protected SmartBranchGroup branch;
	
	public WaveFontObject(String filePath) {
		this.filePath = filePath;
		
		if(this.getCachedBranchGroup() == null || this.getScene() == null) {
			this.setScene(this.createScene());
		    branch = new SmartBranchGroup(this.getScene().getSceneGroup());
		    this.setCachedBranchGroup(branch);
		}
		else {
			branch = this.getCachedBranchGroup().clone();
		}
	}
	
	abstract protected void setScene(Scene s);
	abstract protected Scene getScene();
	
	abstract protected void setCachedBranchGroup(SmartBranchGroup s);
	abstract protected SmartBranchGroup getCachedBranchGroup();
	
	protected Scene createScene() {
		int flags = ObjectFile.RESIZE;
		
//		if (!noTriangulate)
//	      flags |= ObjectFile.TRIANGULATE;
//	    if (!noStripify)
//	      flags |= ObjectFile.STRIPIFY;
		
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
	
	public BranchGroup getSuperGroup() { 
		return branch.getSuperGroup();
	}
	
	public Group getBranchGroup() {
		return branch.getWrapGroup();
	}
}
