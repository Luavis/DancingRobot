
import java.util.ArrayList;

public class MovementBunch extends AbstractMovement {

	private ArrayList<Movement> bunch;
	
	private MovementBunch(double deltaSeconds) {
		bunch = new ArrayList<Movement>();
		this.type = MULTIPLE;
		this.deltaSeconds = deltaSeconds;
	}
	
	private MovementBunch(double deltaSeconds, Movement[] movements) {
		
		this(deltaSeconds);
		
		for(Movement m : movements) {
			bunch.add(m);
		}
	}
	
	@Override
	public void readyAnimation() {
		for(AbstractMovement m : this.bunch) {
			m.readyAnimation();
		}
	}

	@Override
	public MovementBunch clone() {
		// TODO Auto-generated method stub
		MovementBunch clone = new MovementBunch(deltaSeconds);
		clone.setFrontDelay(frontDelay);
		clone.setBackDelay(backDelay);
		clone.bunch = (ArrayList<Movement>)this.bunch.clone();
		
		return clone;
	}

	@Override
	public MovementBunch reverseClone() {
		// TODO Auto-generated method stub
		MovementBunch clone = this.clone();
		
		for(int i = 0; i < clone.bunch.size(); i++) {
			Movement m = (Movement)clone.bunch.get(i);
			clone.bunch.set(i, (Movement)m.reverseClone());
		}
		
		return clone;
	}

	@Override
	public void move(double moveRate) {
		for(AbstractMovement m : this.bunch) {
			m.move(moveRate);
		}
	}

	@Override
	public boolean isSameType(AbstractMovement am) {
		if(!(am instanceof MovementBunch))
			return false;
		
		MovementBunch m = (MovementBunch)am;
		
		return (this.type == m.type && this.bunch.equals(m.bunch));
	}
}
