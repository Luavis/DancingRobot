import java.util.ArrayList;



public class MovementBunch extends Movement {

	private ArrayList<Movement> bunch;
	
	private MovementBunch(SmartBranchGroup smtTarget, int type) {
		super(smtTarget, type);
		bunch = new ArrayList<Movement>();
	}
	
	public MovementBunch(SmartBranchGroup smtTarget) {
		this(smtTarget, Movement.MULTIPLE);
	}
	
	
}
