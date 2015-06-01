import java.io.FileNotFoundException;
import java.net.URL;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;


public class WaveFontObject { // .obj file object in 3d
	
	protected URL filePath;
	protected BranchGroup branch;
	protected Scene scene;
	
	public WaveFontObject(URL filePath) {
		this.filePath = filePath;
		scene = this.createScene();
	    branch = scene.getSceneGroup();
	}
	
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
	
	public BranchGroup getBranchGroup() {
		return branch;
	}
}
