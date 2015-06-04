
public abstract class MovementLazy extends AbstractMovement {
	abstract public Movement[] getMovement();
	
	public void readyAnimation() {
		
	}
	
	public MovementLazy clone() {
		return this;
	}
	
	public MovementLazy reverseClone() {
		return this;
	}
	
	public void move(double moveRate) {
		
	}
}
