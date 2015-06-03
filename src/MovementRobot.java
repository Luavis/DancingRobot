
public class MovementRobot {
	static public Movement[] putHandUp(Robot robot, double angle) {
		return new Movement[]{
				new MovementTransition(robot.leftArmBranch).setX(0.0072222222 * angle).setY(-0.0033333333 * angle),
				new MovementRotation(robot.leftArmBranch).setZ(angle)
				};
	}
}
