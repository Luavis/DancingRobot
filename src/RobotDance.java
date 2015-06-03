
public class RobotDance extends Dance {
	protected Robot target;
	
	public RobotDance(Robot robot) {
		this.target = robot;
		this.initDance();
	}
	
	public void initDance() {
		target
		.getAnimator()
		.start()
		.parallel(0.5, 
					new MovementTransition(target.headBranch).setX(0.1).setY(0.1),
					 new MovementTransition(target.headBranch).setX(-0.1).setY(0.1),
					 new MovementTransition(target.leftArmBranch).setX(0.7).setY(-0.3),
					 new MovementRotation(target.leftArmBranch).setZ(90),
					 new MovementTransition(target.rightArmBranch).setX(-0.7).setY(-0.3),
					 new MovementRotation(target.rightArmBranch).setZ(-90),
					 new MovementRotation(target.bodyBranch).setX(20),
					 new MovementRotation(target.bodyBranch).setX(-20)
				)
		.repeat(4)
		.end()
		.commit();
	}
	
	@Override
	public void getNextFrame() {
		target.getAnimator().getNextFrame();
	}
}
