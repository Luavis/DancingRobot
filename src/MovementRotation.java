
import javax.vecmath.Vector3d;

public class MovementRotation extends Movement {
	
	private MovementRotation(SmartBranchGroup smtTarget, int type, Vector3d vec) {
		super(smtTarget, type, vec);
	}
	
	public MovementRotation(SmartBranchGroup smtTarget) {
		super(smtTarget, Movement.ROTATION);
	}
	
	public MovementRotation(SmartBranchGroup smtTarget, Vector3d vec) {
		this(smtTarget, Movement.ROTATION, vec);
	}
	
	public MovementRotation(SmartBranchGroup smtTarget, double a) {
		this(smtTarget, Movement.ROTATION, new Vector3d(a, a, a));
	}
	
	public MovementRotation(SmartBranchGroup smtTarget, double a, double b, double c) {
		this(smtTarget, Movement.ROTATION, new Vector3d(a, b, c));
	}
}
