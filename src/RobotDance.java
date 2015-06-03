
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
		.parallel(0.6, 
//					new MovementTransition(target.headBranch).setX(1),
//					 new MovementTransition(target.headBranch).setX(-1)
					MovementRobot.putHandUp(target, 60)
				)
		.repeat(100)
		.end()
		.commit();
	}
	
	@Override
	public void getNextFrame() {
		target.getAnimator().getNextFrame();
	}
}
