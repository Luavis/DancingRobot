import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;


public class SmartBranchGroup {
	
	private double rotXDegree = 0.0f;
	private double rotYDegree = 0.0f;
	private double rotZDegree = 0.0f;
	private Vector3d transitionVector = new Vector3d();
	
	
	private BranchGroup wrapBranch = null;
	private TransformGroup transitionAnimationGroup = null;
	private TransformGroup rotationXAnimationGroup = null;
	private TransformGroup rotationYAnimationGroup = null;
	private TransformGroup rotationZAnimationGroup = null;
	
	private Transform3D rotationX = null;
	private Transform3D rotationY = null;
	private Transform3D rotationZ = null;
	private Transform3D transition = null;
	
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
		
		transitionAnimationGroup.setTransform(transition);
		rotationXAnimationGroup.setTransform(rotationX);
		rotationYAnimationGroup.setTransform(rotationY);
		rotationZAnimationGroup.setTransform(rotationZ);
		
		this.transitionAnimationGroup.addChild(rotationXAnimationGroup);
		this.rotationXAnimationGroup.addChild(rotationYAnimationGroup);
		this.rotationYAnimationGroup.addChild(rotationZAnimationGroup);
		this.rotationZAnimationGroup.addChild(wrapBranch);
	}
	
	public Group getSuperGroup() {
		return this.transitionAnimationGroup;
	}
	
	public Group getWrapGroup() {
		return this.wrapBranch;
	}
	
	public Vector3d getRotationVector() {
		return new Vector3d(this.rotXDegree, this.rotYDegree, this.rotZDegree);
	}
	
	public Vector3d	getTransitionVector() {
		return this.transitionVector;
	}
	
	public void rotationX(double degree) {
		this.rotXDegree = degree;
		rotationX.rotX(this.rotXDegree / 180.0f * Math.PI);
		rotationXAnimationGroup.setTransform(rotationX);
	}
	
	public void rotationY(double degree) {
		this.rotYDegree = degree;
		rotationY.rotY(this.rotYDegree / 180.0f * Math.PI);
		rotationYAnimationGroup.setTransform(rotationY);
	}
	
	public void rotationZ(double degree) {
		this.rotZDegree = degree;
		rotationZ.rotZ(this.rotZDegree / 180.0f * Math.PI);
		rotationZAnimationGroup.setTransform(rotationZ);
	}
	
	public void transition(double x, double y, double z) {
		this.transitionVector = new Vector3d(x, y, z);
		this.transition.setTranslation(transitionVector);
		transitionAnimationGroup.setTransform(transition);
	}
}
