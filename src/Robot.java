
import java.util.Hashtable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;


public abstract class Robot extends WaveFontObject implements DancingRobot, Animatable, Danceable {
	
	protected SmartBranchGroup headBranch;
	protected SmartBranchGroup leftArmBranch;
	protected SmartBranchGroup rightArmBranch;
	protected SmartBranchGroup bodyBranch;
	protected Dance dance; 
	protected Animator animator = new Animator();
	
	Transform3D test = new Transform3D();
	
	protected boolean isDancing = false;
	
	protected Robot(String filePath) {
		super(filePath);
	}
	
	protected BranchGroup getRobotPartBranch(Hashtable<String, Shape3D> sceneHash, String groupName) {
		BranchGroup branchGroup = new BranchGroup();
		Shape3D shape = sceneHash.get(groupName);
		branchGroup.addChild(shape.cloneNode(true));
	    
	    return branchGroup;
	}
	
	public void updateOriginVector() {
		headBranch.updateOriginPosition();
		rightArmBranch.updateOriginPosition();
		leftArmBranch.updateOriginPosition();
		bodyBranch.updateOriginPosition();
		branch.updateOriginPosition();
	}
	
	public AbstractMovement[] restoreMovements(double delta) {
		
		AbstractMovement[] ret = {
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector headDelta = headBranch.substractByOrigin();
					return new MovementRotation(headBranch, headDelta.rotationVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector rightArmDelta = rightArmBranch.substractByOrigin();
					return new MovementRotation(rightArmBranch, rightArmDelta.rotationVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector leftArmDelta = leftArmBranch.substractByOrigin();
					return new MovementRotation(leftArmBranch, leftArmDelta.rotationVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector bodyDelta = bodyBranch.substractByOrigin();
					return new MovementRotation(bodyBranch, bodyDelta.rotationVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector totlaDelta = branch.substractByOrigin();
					return new MovementRotation(branch, totlaDelta.rotationVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector headDelta = headBranch.substractByOrigin();
					return new MovementTransition(headBranch, headDelta.transitionVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector rightArmDelta = rightArmBranch.substractByOrigin();
					return new MovementTransition(rightArmBranch, rightArmDelta.transitionVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector leftArmDelta = leftArmBranch.substractByOrigin();
					return new MovementTransition(leftArmBranch, leftArmDelta.transitionVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector bodyDelta = bodyBranch.substractByOrigin();
					return new MovementTransition(bodyBranch, bodyDelta.transitionVector);
				}
			}),
			new MovementLazy(delta, new MovementLazyAction() {
				
				@Override
				public AbstractMovement getMovement() {
					PositionVector totlaDelta = branch.substractByOrigin();
					return new MovementTransition(branch, totlaDelta.transitionVector);
				}
			}),
		};
		
		return ret;
	}
	
	@Override
	public void startDancing() {
		this.isDancing = true;
	}
	
	@Override
	public void stopDancing() {
		this.isDancing = false;
		
	}
	
	@Override
	public void animateNextFrame() {
		if(!this.isDancing)
			return;
		
		if(this.dance != null)
			dance.getNextFrame();
	}
	
	@Override
	public void setDance(Dance dance) {
		this.dance = dance;
	}
	
	@Override
	public Animator getAnimator() {
		return this.animator;
	}
}
