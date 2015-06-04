
import java.util.ArrayList;

public class MovementAndroid {
	
	static public final int LEFT_HAND = 1;
	static public final int RIGHT_HAND = 2;
	
	static public final double X_RATE = 0.01;
	static public final double Y_RATE = -0.006;
	
	static public Movement[] putHandUp(Robot robot, double angle) {
		
		return new Movement[]{
				new MovementTransition(robot.rightArmBranch).setX(-X_RATE * angle).setY(Y_RATE * angle),
				new MovementRotation(robot.rightArmBranch).setZ(-angle),
				new MovementTransition(robot.leftArmBranch).setX(X_RATE * angle).setY(Y_RATE * angle),
				new MovementRotation(robot.leftArmBranch).setZ(angle)
				};
	}
	
	static public Movement[] putHandUp(Robot robot, double angle, int type) {
		SmartBranchGroup smtBranch = null;
		
		double x_rate = 0;
		double y_rate = 0;
		
		if(type == LEFT_HAND) {
			smtBranch = robot.leftArmBranch;
			
			x_rate = X_RATE;
			y_rate = Y_RATE;
		}
		else if(type == RIGHT_HAND) {
			smtBranch = robot.rightArmBranch;
			
			x_rate = -X_RATE;
			y_rate = Y_RATE;
			angle = -angle;
		}
		
		return new Movement[]{
				new MovementTransition(smtBranch).setX(x_rate * angle).setY(y_rate * angle),
				new MovementRotation(smtBranch).setZ(angle)
				};
	}
	
	static public Movement[] putHandUp(Robot robot, double angle, int type, Movement... otherArray) {
		ArrayList<Movement> movement = new ArrayList<Movement>();
		
		SmartBranchGroup smtBranch = null;
		
		double x_rate = 0;
		double y_rate = 0;
		
		
		if(type == LEFT_HAND) {
			smtBranch = robot.leftArmBranch;
			
			x_rate = X_RATE;
			y_rate = Y_RATE;
		}
		else if(type == RIGHT_HAND) {
			smtBranch = robot.rightArmBranch;
			
			x_rate = -X_RATE;
			y_rate = Y_RATE;
			angle = -angle;
		}
		
		movement.add(new MovementTransition(smtBranch).setX(x_rate * angle).setY(y_rate * angle));
		movement.add(new MovementRotation(smtBranch).setZ(angle));
		
		for(Movement mv : otherArray) {
			movement.add(mv);
		}
		
		return (Movement[]) movement.toArray(otherArray);
	}
	
	static public Movement[] putHandUp(Robot robot, double angle, Movement... otherArray) {
		ArrayList<Movement> movement = new ArrayList<Movement>();
		
		movement.add(new MovementTransition(robot.leftArmBranch).setX(X_RATE * angle).setY(Y_RATE * angle));
		movement.add(new MovementRotation(robot.leftArmBranch).setZ(angle));
		
		movement.add(new MovementTransition(robot.rightArmBranch).setX(X_RATE * angle).setY(Y_RATE * angle));
		movement.add(new MovementRotation(robot.rightArmBranch).setZ(angle));
		
		for(Movement mv : otherArray) {
			movement.add(mv);
		}
		
		return (Movement[]) movement.toArray(otherArray);
	}
}
