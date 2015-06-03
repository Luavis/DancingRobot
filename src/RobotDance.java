
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
		.parallel(0.2, 
					new MovementTransition(target.headBranch).setX(0.1).setY(0.1),
					 new MovementTransition(target.headBranch).setX(-0.1).setY(0.1),
					 new MovementTransition(target.leftArmBranch).setX(0.7).setY(-0.3),
					 new MovementRotation(target.leftArmBranch).setZ(90),
					 new MovementTransition(target.rightArmBranch).setX(-0.7).setY(-0.3),
					 new MovementRotation(target.rightArmBranch).setZ(-90),
					 new MovementRotation(target.bodyBranch).setX(20),
					 new MovementRotation(target.bodyBranch).setX(-20)
				)
		.parallel(0.3, new MovementRotation(target.branch).setZ(30))
		.repeat(4)
		.end()
		.parallel(0.3, 
					MovementRobot.putHandUp(target, 90),
					new MovementRotation(target.branch).setZ(30)
				)
		.parallel(0.5, new MovementRotation(target.branch).setY(360),
				  new MovementTransition(target.branch).setY(1),
				  new MovementTransition(target.branch).setY(-1)
				)
		.repeat(3, false)
		.parallel(0.5, new MovementRotation(target.branch).setZ(-30)
				)
		.commit();
	}
	
	@Override
	public void getNextFrame() {
		target.getAnimator().getNextFrame();
	}
}
