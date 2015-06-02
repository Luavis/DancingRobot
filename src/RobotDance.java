
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
					new MovementTransition(target.headBranch).setX(0.4),
					 new MovementTransition(target.headBranch).setX(-0.4)
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
