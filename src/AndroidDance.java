
public class AndroidDance extends RobotDance {

	public AndroidDance(Android robot) {
		super(robot);
	}

	@Override
	public void initDance() {
		// TODO Auto-generated method stub
		target
		.getAnimator()
//		.parallel(0.2, MovementAndroid.putHandUp(target, 50)
//				)
		.parallel(0.5, new MovementRotation(target.leftArmBranch).setX(40),
					new MovementRotation(target.leftArmBranch).setX(-40),
					new MovementRotation(target.rightArmBranch).setX(40),
					new MovementRotation(target.rightArmBranch).setX(-40),
					new MovementRotation(target.branch).setY(20)
				)
		.repeat(5)
		.parallel(0.2, MovementAndroid.putHandUp(target, 90),
					new MovementTransition(target.headBranch).setX(0.1),
					 new MovementTransition(target.headBranch).setX(-0.1)
//					 new MovementRotation(target.headBranch).setX(20),
//					 new MovementRotation(target.headBranch).setX(-20),
//					 new MovementRotation(target.bodyBranch).setX(20),
//					 new MovementRotation(target.bodyBranch).setX(-20)
				)
		.repeat(5)
		.start()
		.parallel(0.3, new MovementRotation(target.leftArmBranch).setX(-90),
					new MovementTransition(target.leftArmBranch).setZ(0.2).setY(0.2),
					new MovementRotation(target.rightArmBranch).setX(-90),
					new MovementTransition(target.rightArmBranch).setZ(0.2).setY(0.2),
					new MovementTransition(target.headBranch).setY(0.2)
				)
		.repeat(5)
		.end()
		.parallel(0.1, MovementAndroid.putHandUp(target, 100)
				)
		.parallel(0.1, new MovementTransition(target.leftArmBranch).setY(0.2),
				new MovementTransition(target.rightArmBranch).setY(0.2))
		.start()
		.parallel(0.7, //MovementAndroid.putHandUp(target, 20),
//					MovementAndroid.putHandUp(target, -20),
					new MovementTransition(target.headBranch).setX(0.3).setY(0.3),
					new MovementTransition(target.headBranch).setX(-0.3).setY(-0.3),
					new MovementTransition(target.headBranch).setX(-0.3).setY(0.3),
					new MovementTransition(target.headBranch).setX(0.3).setY(-0.3),
					new MovementTransition(target.bodyBranch).setX(0.3).setY(0.3),
					new MovementTransition(target.bodyBranch).setX(-0.3).setY(-0.3),
					new MovementTransition(target.bodyBranch).setX(-0.3).setY(0.3),
					new MovementTransition(target.bodyBranch).setX(0.3).setY(-0.3),
					new MovementTransition(target.leftArmBranch).setX(0.5).setY(0.5),
					new MovementTransition(target.leftArmBranch).setX(-0.5).setY(-0.5),
					new MovementTransition(target.leftArmBranch).setX(-0.2).setY(0.2),
					new MovementTransition(target.leftArmBranch).setX(0.2).setY(-0.2),
					new MovementTransition(target.rightArmBranch).setX(0.2).setY(0.2),
					new MovementTransition(target.rightArmBranch).setX(-0.2).setY(-0.2),
					new MovementTransition(target.rightArmBranch).setX(-0.5).setY(0.5),
					new MovementTransition(target.rightArmBranch).setX(0.5).setY(-0.5)
				)
		.repeat(3, false)
		.end()
		.parallel(0.2, MovementAndroid.putHandUp(target, -100),
					new MovementTransition(target.leftArmBranch).setY(-0.35).setX(-0.02),
					new MovementTransition(target.rightArmBranch).setX(-0.15)
				)
		.parallel(0.2, new MovementRotation(target.branch).setY(-80)
				)
		.parallel(0.2, new MovementRotation(target.leftArmBranch).setX(-50),
					new MovementRotation(target.rightArmBranch).setX(-50),
					new MovementTransition(target.headBranch).setZ(0.4),
					new MovementRotation(target.bodyBranch).setX(10)
				)
		.repeat(5)
		.parallel(0.2, new MovementRotation(target.branch).setY(160)
				)
		.parallel(0.2, new MovementRotation(target.leftArmBranch).setX(-50),
					new MovementRotation(target.rightArmBranch).setX(-50),
					new MovementTransition(target.headBranch).setZ(0.4),
					new MovementRotation(target.bodyBranch).setX(10)
				)
		.repeat(5)
		.parallel(0.2, new MovementRotation(target.branch).setY(-80)
				)
//		.parallel(0.1, MovementAndroid.putHandUp(target, 90)
//				)
//		.parallel(0.1, new MovementRotation(target.branch).setZ(30)
//				)
//		.parallel(0.5, new MovementRotation(target.branch).setY(360),
//				  new MovementTransition(target.branch).setY(1),
//				  new MovementTransition(target.branch).setY(-1)
//				)
//		.repeat(3, false)
//		.parallel(0.5, new MovementRotation(target.branch).setZ(-30)
//				)
//		.parallel(0.5, new MovementRotation(target.branch).setY(360)
//				)
//		.repeat(3, false)
//		.parallel(0.5, new MovementRotation(target.branch).setZ(120)
//				)
//		.parallel(0.3, new MovementRotation(target.branch).setY(360)
//				 )
//		.repeat(3, false)
//		.parallel(0.2, new MovementRotation(target.branch).setZ(-120)
//				)
//		.parallel(0.2, MovementAndroid.putHandUp(target, -90))
		.commit();
	}
	
}
