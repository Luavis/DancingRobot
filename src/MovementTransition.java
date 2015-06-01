import javax.vecmath.Vector3d;


public class MovementTransition extends Movement {

	private MovementTransition(SmartBranchGroup smtTarget, int type, Vector3d vec) {
		super(smtTarget, type, vec);
	}
	
	public MovementTransition(SmartBranchGroup smtTarget) {
		super(smtTarget, Movement.TRANS);
	}
	
	public MovementTransition(SmartBranchGroup smtTarget, Vector3d vec) {
		this(smtTarget, Movement.TRANS, vec);
	}
	
	public MovementTransition(SmartBranchGroup smtTarget, double a) {
		this(smtTarget, Movement.TRANS, new Vector3d(a, a, a));
	}
	
	public MovementTransition(SmartBranchGroup smtTarget, double a, double b, double c) {
		this(smtTarget, Movement.TRANS, new Vector3d(a, b, c));
	}
}
