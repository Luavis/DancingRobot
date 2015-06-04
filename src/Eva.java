
import java.util.Hashtable;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;


public class Eva extends Robot {
	public Eva() {
		super(Resources.evaRobotPath);
		
		Hashtable<String, Shape3D> sceneHash = (Hashtable<String, Shape3D>)this.scene.getNamedObjects();
	    
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
}
