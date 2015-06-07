
public abstract class AbstractMovement implements Cloneable {
	
	static private int DELTA_SECONDS_DEFAULT = -1;
	protected int type;
	
	static public int ROTATION = 1;
	static public int TRANS = 2; 
	static public int MULTIPLE = 3; // future
	
	protected double frontDelay = 0.0f;
	protected double backDelay = 0.0f;
	
	protected double frontDelayRate = 0.0f;
	protected double backDelayRate = 0.0f;
	
	protected double deltaSeconds = DELTA_SECONDS_DEFAULT; // cleared by animator soon
	
	public int getType() {
		return type;
	}
	
	public double getFrontDelay() {
		return this.frontDelay;
	}
	
	public double getBackDelay() {
		return backDelay;
	}
	
	public void setFrontDelay(double frontDelay) {
		this.frontDelay = frontDelay;
		this.frontDelayRate = this.frontDelay / this.totalTime();
	}
	
	public void setBackDelay(double backDelay) {
		this.backDelay = backDelay;
		this.backDelayRate = this.backDelay / this.totalTime();
	}
	
	public double totalTime() {
		return this.deltaSeconds + this.frontDelay + this.backDelay;
	}
	
	public AbstractMovement setDeltaSecond(double velocity) {
		this.deltaSeconds = velocity;
		return this;
	}
	
	public double getDeltaSecond() {
		return deltaSeconds;
	}
	
	public boolean hasDeltaSecond() {
		return (deltaSeconds != DELTA_SECONDS_DEFAULT);
	}
	
	abstract public void readyAnimation();
	
	abstract public AbstractMovement clone();
	abstract public AbstractMovement reverseClone();
	abstract public void move(double moveRate);
	abstract public boolean isSameType(AbstractMovement m);
}
