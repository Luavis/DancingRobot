
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class SmartBranchGroup implements Cloneable {

	private BranchGroup superBranch = new BranchGroup();
	
	private Vector3d transitionVector = new Vector3d();
	private Vector3d rotationVector = new Vector3d(0, 0, 0);
	
	private BranchGroup wrapBranch = null;
	private TransformGroup transitionAnimationGroup = null;
	private TransformGroup rotationXAnimationGroup = null;
	private TransformGroup rotationYAnimationGroup = null;
	private TransformGroup rotationZAnimationGroup = null;
	
	private Transform3D rotationX = null;
	private Transform3D rotationY = null;
	private Transform3D rotationZ = null;
	private Transform3D transition = null;
	
	private PositionVector originPVector = null;
	
	private PositionVector pVector = null;
	
	public SmartBranchGroup(BranchGroup branch) {
		this.wrapBranch = branch;
		
		this.transitionAnimationGroup= new TransformGroup();
		this.rotationXAnimationGroup= new TransformGroup();
		this.rotationYAnimationGroup= new TransformGroup();
		this.rotationZAnimationGroup= new TransformGroup();
		
		this.transitionAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.rotationXAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.rotationYAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.rotationZAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		this.transition = new Transform3D();
		this.rotationX = new Transform3D();
		this.rotationY = new Transform3D();
		this.rotationZ = new Transform3D();
		
		this.pVector = new PositionVector(transitionVector, rotationVector);
		this.updateOriginPosition();
		
		transitionAnimationGroup.setTransform(transition);
		rotationXAnimationGroup.setTransform(rotationX);
		rotationYAnimationGroup.setTransform(rotationY);
		rotationZAnimationGroup.setTransform(rotationZ);
		
		this.transitionAnimationGroup.addChild(rotationXAnimationGroup);
		this.rotationXAnimationGroup.addChild(rotationYAnimationGroup);
		this.rotationYAnimationGroup.addChild(rotationZAnimationGroup);
		this.rotationZAnimationGroup.addChild(wrapBranch);
		
		BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0, 0, 0), 1000);
		TransformGroup rootTransformGroup = new TransformGroup();
		rootTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    rootTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    
	    MouseTranslate mouseTrans = new MouseTranslate();
	    mouseTrans.setTransformGroup(rootTransformGroup);
	    mouseTrans.setSchedulingBounds(boundingSphere);
	    rootTransformGroup.addChild(mouseTrans);
	    rootTransformGroup.addChild(this.transitionAnimationGroup);
	    
		this.superBranch.addChild(rootTransformGroup);
	}
	
	public BranchGroup getSuperGroup() {
		return this.superBranch;
	}
	
	public void updateOriginPosition() {
		this.originPVector = new PositionVector(this.getTransitionVector(), this.getRotationVector());
	}
	
	public PositionVector substractByOrigin() {
		return this.pVector.substractPoition(this.originPVector);
	}
	
	public Group getWrapGroup() {
		return this.wrapBranch;
	}
	
	public Vector3d getRotationVector() {
		return (Vector3d)rotationVector.clone();
	}
	
	public Vector3d	getTransitionVector() {
		return (Vector3d)this.transitionVector.clone();
	}
	
	public void rotationX(double degree) {
		this.rotationVector.x = degree;
		rotationX.rotX(this.rotationVector.x / 180.0f * Math.PI);
		rotationXAnimationGroup.setTransform(rotationX);
	}
	
	public void rotationY(double degree) {
		this.rotationVector.y = degree;
		rotationY.rotY(this.rotationVector.y / 180.0f * Math.PI);
		rotationYAnimationGroup.setTransform(rotationY);
	}
	
	public void rotationZ(double degree) {
		this.rotationVector.z = degree;
		rotationZ.rotZ(this.rotationVector.z / 180.0f * Math.PI);
		rotationZAnimationGroup.setTransform(rotationZ);
	}
	
	public void transition(double x, double y, double z) {
		this.transitionVector.x = x;
		this.transitionVector.y = y;
		this.transitionVector.z = z;
		
		this.transition.setTranslation(transitionVector);
		transitionAnimationGroup.setTransform(transition);
	}
	
	@Override
	protected SmartBranchGroup clone() {
		BranchGroup bClone = new BranchGroup();
		bClone .addChild(wrapBranch.cloneNode(true));
		SmartBranchGroup clone = new SmartBranchGroup(bClone);
		
		return clone;
	}
}
