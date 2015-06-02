
public class RobotDance extends Dance {
	protected Robot target;
	
	public RobotDance(Robot robot) {
		this.target = robot;
		this.initDance();
	}
	
	public void initDance() {
		
		target
		.getAnimator()
		.parallel(1, new MovementRotation(target.leftArmBranch).setZ(90).setDeltaSecond(1),
					  new MovementTransition(target.leftArmBranch).setX(0.7).setY(-0.3).setDeltaSecond(1)
				)
		.reverse()
		.commit();
	}
	
	@Override
	public void getNextFrame() {
		target.getAnimator().getNextFrame();
	}
}
