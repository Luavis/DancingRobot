import javax.vecmath.Vector3d;


public class Movement implements Cloneable {
	static public int ROTATION = 1;
	static public int TRANS = 2;
	static private int DELTA_SECONDS_DEFAULT = -1;
	
	private int type;
	private Vector3d originVec;
	private Vector3d vec;
	private SmartBranchGroup smtTarget;
	private double deltaSeconds = DELTA_SECONDS_DEFAULT; // cleared by animator soon
	
	private double frontDelay = 0.0f;
	private double backDelay = 0.0f;
	
	private double frontDelayRate = 0.0f;
	private double backDelayRate = 0.0f;
	
//	private double deltaRate = 1.0f;
	
	public Movement(SmartBranchGroup smtTarget, int type) {
		this(smtTarget, type, new Vector3d(), -1);
	}
	
	public Movement(SmartBranchGroup smtTarget, int type, Vector3d vec) {
		this(smtTarget, type, vec, -1);
	}
	
	public Movement(SmartBranchGroup smtTarget, int type, Vector3d vec, double seconds) {
		this.smtTarget = smtTarget;
		this.type = type;
		this.vec = vec;
		this.deltaSeconds = seconds;
		
		if(type == ROTATION) {
			originVec = this.smtTarget.getRotationVector();
		}
		else if(type == TRANS) {
			originVec = this.smtTarget.getTransitionVector();
		}
	}
	
	public void reloadOriginVector() {
		if(type == ROTATION) {
			originVec = this.smtTarget.getRotationVector();
		}
		else if(type == TRANS) {
			originVec = this.smtTarget.getTransitionVector();
		}
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
	
	public Vector3d getOriginVector() {
		return originVec;
	}
	
	public int getType() {
		return type;
	}
	
	public SmartBranchGroup getSmtTarget() {
		return smtTarget;
	}
	
	
	public Vector3d getVec() {
		return vec;
	}
	
	public Movement setX(double x) {
		this.vec.x = x;
		return this;
	}
	
	public Movement setY(double y) {
		this.vec.y = y;
		return this;
	}
	
	public Movement setZ(double z) {
		this.vec.z = z;
		return this;
	}
	
	public Movement setDeltaSecond(double velocity) {
		this.deltaSeconds = velocity;
		return this;
	}
	
	public double getDeltaSecond() {
		return deltaSeconds;
	}
	
	public boolean hasDeltaSecond() {
		return (deltaSeconds != DELTA_SECONDS_DEFAULT);
	}
	
	public boolean isSameType(Movement m) {
		return (this.smtTarget != null && this.smtTarget == m.smtTarget && this.type == m.type);
	}
	
	public double totalTime() {
		return this.deltaSeconds + this.frontDelay + this.backDelay;
	}
	
	public void move(double moveRate) {
		
		if(this.frontDelayRate > moveRate) { 
			this.reloadOriginVector(); // when front delay some movement change origin target vector
		}
		if( this.frontDelayRate > moveRate || 1 - this.backDelayRate < moveRate)
			return; // delay
		
		double tranisitionRate = (moveRate - frontDelayRate) /  ( 1 - backDelayRate - frontDelayRate);
		
		double x = originVec.x + vec.x * tranisitionRate;
		double y = originVec.y + vec.y * tranisitionRate;
		double z = originVec.z + vec.z * tranisitionRate;
		
		if(this.type == ROTATION) {
			this.smtTarget.rotationX(x);
			this.smtTarget.rotationY(y);
			this.smtTarget.rotationZ(z);
		}
		else if(this.type == TRANS) {
			this.smtTarget.transition(x, y, z);
		}
	}
	
//	public void setDelatRate(double rate) {
//		
//	}
	
	@Override
	public Movement clone() {
		
		Movement newM = new Movement(this.smtTarget, this.type);
		newM.setX(this.vec.x).setY(this.vec.y).setZ(this.vec.z).setDeltaSecond(this.deltaSeconds);
		
		return newM;
	}
	
	public Movement reverseClone() {
		
		Movement newM = new Movement(this.smtTarget, this.type);
		newM.setX(-1 * this.vec.x).setY(-1 * this.vec.y).setZ(-1 * this.vec.z).setDeltaSecond(this.deltaSeconds);
		
		return newM;
	}
}

