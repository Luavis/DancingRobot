
import java.util.Hashtable;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;

import com.sun.j3d.loaders.Scene;


public class Eva extends Robot {
	private static Scene scene = null;
	static private SmartBranchGroup cachedBranchGroup = null;
	
	public Eva() {
		super(Resources.evaRobotPath);
		
		@SuppressWarnings("unchecked")
		Hashtable<String, Shape3D> sceneHash = (Hashtable<String, Shape3D>)this.getScene().getNamedObjects();
	    
	    this.branch = new SmartBranchGroup(new BranchGroup()); // clear branch of parent
	    
	    leftArmBranch = new SmartBranchGroup(this.getRobotPartBranch(sceneHash, Constants.ROBOT_LEFT_GROUP));
	    this.getBranchGroup().addChild(leftArmBranch .getSuperGroup());
	    
	    rightArmBranch = new SmartBranchGroup(this.getRobotPartBranch(sceneHash, Constants.ROBOT_RIGHT_GROUP));
	    this.getBranchGroup().addChild(rightArmBranch.getSuperGroup());
	    
	    BranchGroup head1 = this.getRobotPartBranch(sceneHash, Constants.ROBOT_HEAD_1_GROUP);
	    BranchGroup head2 = this.getRobotPartBranch(sceneHash, Constants.ROBOT_HEAD_2_GROUP);
	    BranchGroup headBranch = new BranchGroup();
	    
	    headBranch.addChild(head1);
	    headBranch.addChild(head2);
	    
	    this.headBranch = new SmartBranchGroup(headBranch);
	    
	    this.getBranchGroup().addChild(this.headBranch.getSuperGroup());
	    
	    bodyBranch = new SmartBranchGroup(this.getRobotPartBranch(sceneHash, Constants.ROBOT_BODY_GROUP));
	    this.getBranchGroup().addChild(bodyBranch.getSuperGroup());
	}
	
	@Override
	protected void setScene(Scene s) {
		Eva.scene = s; 
	}
	
	@Override
	protected Scene getScene() {
		return Eva.scene;
	}

	@Override
	protected void setCachedBranchGroup(SmartBranchGroup s) {
		Eva.cachedBranchGroup = s;
	}

	@Override
	protected SmartBranchGroup getCachedBranchGroup() {
		return Eva.cachedBranchGroup;
	}
	
	
}
