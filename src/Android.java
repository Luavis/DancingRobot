import java.util.Hashtable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;


public class Android extends Robot {
	public Android() {
		super(Resources.androidRobotPath);
		
		Hashtable<String, Shape3D> sceneHash = (Hashtable<String, Shape3D>)this.scene.getNamedObjects();
	    
	    this.branch = new SmartBranchGroup(new BranchGroup()); // clear branch of parent
	    
	    leftArmBranch = new SmartBranchGroup(this.getRobotPartBranch(sceneHash, Constants.ANDROID_LEFT_ARM_GROUP));
	    this.getBranchGroup().addChild(leftArmBranch .getSuperGroup());
	    
	    rightArmBranch = new SmartBranchGroup(this.getRobotPartBranch(sceneHash, Constants.ANDROID_RIGHT_ARM_GROUP));
	    this.getBranchGroup().addChild(rightArmBranch.getSuperGroup());
	    
	    BranchGroup left_antenna = this.getRobotPartBranch(sceneHash, Constants.ANDROID_LEFT_ANTENNA_GROUP);
	    BranchGroup right_antenna = this.getRobotPartBranch(sceneHash, Constants.ANDROID_RIGHT_ANTENNA_GROUP);
	    
	    BranchGroup left_eye = this.getRobotPartBranch(sceneHash, Constants.ANDROID_LEFT_EYE_GROUP);
	    BranchGroup right_eye = this.getRobotPartBranch(sceneHash, Constants.ANDROID_RIGHT_EYE_GROUP);
	    
	    BranchGroup head = this.getRobotPartBranch(sceneHash, Constants.ANDROID_HEAD_GROUP);
	    
	    BranchGroup headBranch = new BranchGroup();
	    
	    headBranch.addChild(head);
	    headBranch.addChild(left_antenna);
	    headBranch.addChild(right_antenna);
	    headBranch.addChild(left_eye);
	    headBranch.addChild(right_eye);
	    
	    this.headBranch = new SmartBranchGroup(headBranch);
	    this.getBranchGroup().addChild(this.headBranch.getSuperGroup());
	    
	    BranchGroup left_leg = this.getRobotPartBranch(sceneHash, Constants.ANDROID_LEFT_LEG_GROUP);
	    BranchGroup right_leg = this.getRobotPartBranch(sceneHash, Constants.ANDROID_RIGHT_LEG_GROUP);
	    BranchGroup body = this.getRobotPartBranch(sceneHash, Constants.ANDROID_BODY_GROUP);
	    
	    BranchGroup bodyBranch = new BranchGroup();
	    
	    bodyBranch.addChild(left_leg);
	    bodyBranch.addChild(right_leg);
	    bodyBranch.addChild(body);
	    
	    this.bodyBranch = new SmartBranchGroup(bodyBranch);
	    this.getBranchGroup().addChild(this.bodyBranch.getSuperGroup());
	}
}
