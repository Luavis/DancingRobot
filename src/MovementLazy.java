
public class MovementLazy extends AbstractMovement {
	public MovementLazyAction movementAction;
	private Movement movement;
	
	public MovementLazy(double deltaSeconds, MovementLazyAction action) {
		this.movementAction = action;
		this.deltaSeconds = deltaSeconds;
	}
	
	public void readyAnimation() {
		movement =  (Movement)movementAction.getMovement();
		
		movement.setDeltaSecond(this.deltaSeconds);
		movement.setFrontDelay(this.frontDelay);
		movement.setBackDelay(this.backDelay);
		
		movement.readyAnimation();
	}
	
	public MovementLazy clone() {
		throw(new IllegalAccessError("MovementLazy class not support clone"));
	}
	
	
	public MovementLazy reverseClone() {
		throw(new IllegalAccessError("MovementLazy class not support reverse clone"));
	}
	
	public void move(double moveRate) {
		movement.move(moveRate);
	}
	
	public boolean isSameType(AbstractMovement m) {
		return false; // Lazy can not check
	}
}
