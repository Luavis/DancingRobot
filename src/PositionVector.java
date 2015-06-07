
import javax.vecmath.Vector3d;


public class PositionVector {
	public Vector3d transitionVector;
	public Vector3d rotationVector;
	
	public PositionVector(Vector3d transitionVector, Vector3d rotationVector) {
		this.transitionVector = transitionVector;
		this.rotationVector = rotationVector;
	}
	
	public PositionVector substractPoition(PositionVector v) {
		
		Vector3d t1 = v.transitionVector;
		Vector3d r1 = v.rotationVector;
		
		Vector3d t2 = this.transitionVector;
		Vector3d r2 = this.rotationVector;
		
		return new PositionVector(new Vector3d(t1.x - t2.x, t1.y - t2.y, t1.z - t2.z), new Vector3d(r1.x - r2.x, r1.y - r2.y, r1.z - r2.z));
	}
	
	static public Vector3d substractVector(Vector3d v1, Vector3d v2) {
		return new Vector3d(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
	}
}
