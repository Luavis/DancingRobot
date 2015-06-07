import java.net.URL;
import java.util.Hashtable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3d;


public abstract class Robot extends WaveFontObject implements DancingRobot, Animatable, Danceable {
	
	protected SmartBranchGroup headBranch;
	protected SmartBranchGroup leftArmBranch;
	protected SmartBranchGroup rightArmBranch;
	protected SmartBranchGroup bodyBranch;
	protected Dance dance; 
	protected Animator animator = new Animator();
	
	Transform3D test = new Transform3D();
	
	protected boolean isDancing = false;
	
	protected Robot(String filePath) {
		super(filePath);
	}
	
	protected BranchGroup getRobotPartBranch(Hashtable<String, Shape3D> sceneHash, String groupName) {
		BranchGroup branchGroup = new BranchGroup();
		Shape3D shape = sceneHash.get(groupName);
		branchGroup.addChild(shape.cloneNode(true));
	    
	    return branchGroup;
	}
	
	@Override
	public void startDancing() {
		this.isDancing = true;
	}
	
	@Override
	public void stopDancing() {
		this.isDancing = false;
		
	}
	
	@Override
	public void animateNextFrame() {
		if(!this.isDancing)
			return;
		
		if(this.dance != null)
			dance.getNextFrame();
	}
	
	@Override
	public void setDance(Dance dance) {
		this.dance = dance;
	}
	
	@Override
	public Animator getAnimator() {
		return this.animator;
	}
}
