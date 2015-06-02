
public class RobotDance extends Dance {
	protected Robot target;
	
	public RobotDance(Robot robot) {
		this.target = robot;
		this.initDance();
	}
	
	public void initDance() {
		target
		.getAnimator()
//		.parallel(0.5, new MovementRotation(target.leftArmBranch).setZ(90).setDeltaSecond(0.5),
//					 new MovementTransition(target.leftArmBranch).setX(0.7).setY(-0.3).setDeltaSecond(0.5),
//					 new MovementRotation(target.rightArmBranch).setZ(-90).setDeltaSecond(0.5),
//					 new MovementTransition(target.rightArmBranch).setX(-0.7).setY(-0.3).setDeltaSecond(0.5)
//				)
//		.repeat(2)
		.parallel(0.3, new MovementTransition(target.headBranch).setY(0.1).setDeltaSecond(0.3)
				)
		.parallel(0.2, new MovementTransition(target.headBranch).setX(0.3).setDeltaSecond(0.2)
				)
		.repeat(1)
		.repeat(1)
		.commit();
		
	}
	
	@Override
	public void getNextFrame() {
		target.getAnimator().getNextFrame();
	}
}
