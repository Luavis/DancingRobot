
public abstract class RobotDance extends Dance {
	protected Robot target;
	
	public RobotDance(Robot robot) {
		this.target = robot;
		this.initDance();
	}
	
	public abstract void initDance();
	
	@Override
	public void getNextFrame() {
		target.getAnimator().getNextFrame();
	}
}
