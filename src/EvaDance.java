
public class EvaDance extends RobotDance {

	public EvaDance(Eva robot) {
		super(robot);
	}
	
	@Override
	public void initDance() {
		target
		.getAnimator()
//		.start()
//		.parallel(0.2, MovementEva.putHandUp(target, 90),
//					new MovementTransition(target.headBranch).setX(0.1).setY(0.1),
//					 new MovementTransition(target.headBranch).setX(-0.1).setY(0.1),
//					 new MovementRotation(target.bodyBranch).setX(20),
//					 new MovementRotation(target.bodyBranch).setX(-20)
//				)
//		.repeat(4)
//		.end()
		.parallel(0.1, MovementEva.putHandUp(target, 90)
				)
		.parallel(0.1, new MovementRotation(target.branch).setZ(30)
				)
		.parallel(0.5, new MovementRotation(target.branch).setY(360),
				  new MovementTransition(target.branch).setY(1),
				  new MovementTransition(target.branch).setY(-1)
				)
		.repeat(3, false)
		.parallel(0.5, new MovementRotation(target.branch).setZ(-30)
				)
		.parallel(0.5, new MovementRotation(target.branch).setY(360)
				)
		.repeat(3, false)
		.parallel(0.5, new MovementRotation(target.branch).setZ(120)
				)
		.parallel(0.3, new MovementRotation(target.branch).setY(360)
				 )
		.repeat(3, false)
		.parallel(0.2, new MovementRotation(target.branch).setZ(-120)
				)
		.parallel(0.2, MovementEva.putHandUp(target, -80)
				)
		.parallel(0.5,
					new MovementTransition(target.branch).setY(0.7).setZ(0.7),
					new MovementTransition(target.branch).setY(0.6).setZ(-0.6),
					new MovementTransition(target.branch).setY(-0.5).setZ(-0.5),
					new MovementTransition(target.branch).setY(-0.3).setZ(0.3),
					new MovementRotation(target.branch).setX(360)
				)
		.parallel(0.2, new MovementTransition(target.branch).setY(+0.2).setZ(-0.2))
		.start()
		.parallel(0.5, MovementEva.putHandUp(target, 20),
			MovementEva.putHandUp(target, -20),
			new MovementRotation(target.headBranch).setY(360)
			)
		.repeat(3, false)
		.end()
		.commit();
	}

}
