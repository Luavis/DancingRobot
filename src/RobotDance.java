
public class RobotDance extends Dance {
	protected Robot target;
	
	public RobotDance(Robot robot) {
		this.target = robot;
		this.initDance();
	}
	
	public void initDance() {
		target
		.getAnimator()
//		.parallel(3, new MovementRotation(target.branch).setY(360).setDeltaSecond(3))
//		.repeat(10)
//		.parallel(1, new MovementRotation(target.leftArmBranch).setZ(90).setDeltaSecond(1),
//					 new MovementTransition(target.leftArmBranch).setX(0.7).setY(-0.3).setDeltaSecond(1),
//					 new MovementRotation(target.rightArmBranch).setZ(-90).setDeltaSecond(1),
//					 new MovementTransition(target.rightArmBranch).setX(-0.7).setY(-0.3).setDeltaSecond(1)
//				)
//		.repeat(1)
		.parallel(0.4, new MovementTransition(target.headBranch).setX(0.3).setDeltaSecond(0.2))
		.repeat(5)
		.commit();
		
	}
	
	@Override
	public void getNextFrame() {
		target.getAnimator().getNextFrame();
	}
}
