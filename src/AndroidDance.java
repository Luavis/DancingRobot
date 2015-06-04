
public class AndroidDance extends RobotDance {

	public AndroidDance(Android robot) {
		super(robot);
	}

	@Override
	public void initDance() {
		// TODO Auto-generated method stub
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
		.repeat(4)
		.end()
		.parallel(0.1, MovementAndroid.putHandUp(target, 90)
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
		.parallel(0.2, MovementAndroid.putHandUp(target, -90))
		.commit();
	}
	
}
