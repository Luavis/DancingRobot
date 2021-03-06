
import java.util.ArrayList;
import java.util.Stack;

public class Animator {
	
	private ArrayList<AbstractMovement[]> movements = new ArrayList<AbstractMovement[]>();
	private int currentMoveIndex;
	private ArrayList<Double> commonVelocities = new ArrayList<Double>();
	private double fps = 30.0f;
	private double currentFrame = 1;
	
	private boolean infRepeat = true;
	
	private boolean isStartedPoint = false;
	private Stack<Integer> startIndexesStack = new Stack<Integer>();
	
	public Animator() {
		this(AnimationManager.FPS);
	}
	
	public Animator(double fps) {
		this.fps = fps;
	}
	
	public Animator parallel(Movement... ms) {
		return this.parallel(1, ms);
	}
	
	public Animator start() {
		isStartedPoint = true;
		startIndexesStack.add(movements.size()); // last index + 1 for new start
		
		return this;
	}

	public Animator end() {
		if(!isStartedPoint) {
			throw(new IllegalStateException("It dosen't start"));
		}
		
		startIndexesStack.pop(); // pop value
		
		if(startIndexesStack.size() == 0) {
			isStartedPoint = false;
		}
		
		return this;
	}
	
	public Animator setInfRepeat(boolean infRepeat) {
		this.infRepeat = infRepeat;
		return this;
	}
	
	public Animator parallel(double commonVelocity, Movement... ms) {
		commonVelocities.add(commonVelocity);
		movements.add(ms);
		
		return this;
	}
	
	public Animator parallel(double commonVelocity, AbstractMovement[] arrayMs, AbstractMovement... ms) {
		commonVelocities.add(commonVelocity);
		ArrayList<AbstractMovement> mList = new ArrayList<AbstractMovement>();
		
		for(AbstractMovement m : ms)
			mList.add(m);
		
		for(AbstractMovement m : arrayMs)
			mList.add(m);
		
		movements.add(mList.toArray(arrayMs));
		
		return this;
	}
	
	public Animator parallel(double commonVelocity, AbstractMovement[] arrayMs, AbstractMovement[] arrayMs2,  AbstractMovement... ms) {
		commonVelocities.add(commonVelocity);
		ArrayList<AbstractMovement> mList = new ArrayList<AbstractMovement>();
		
		for(AbstractMovement m : ms)
			mList.add(m);
		
		for(AbstractMovement m : arrayMs2)
			mList.add(m);
		
		for(AbstractMovement m : arrayMs)
			mList.add(m);
		
		movements.add(mList.toArray(arrayMs));
		
		return this;
	}
	
	public Animator repeat(int cnt) {
		return this.repeat(cnt, true);
	}
	
	public int getLastStartIndex() {
		return this.startIndexesStack.lastElement();
	}
	
	public int getCurrentEndIndex() {
		return this.movements.size() - 1;
	}
	
	// Magic, Do not touch it.
	
	public Animator repeat(int cnt, boolean reverse) {
		
		if(!reverse) { // get last
			if(!isStartedPoint) {
				double commonVelocity = commonVelocities.get(commonVelocities.size() - 1);
				AbstractMovement[] ms = movements.get(movements.size() - 1);
				
				for(int i = 0; i < cnt; i++) {
					commonVelocities.add(commonVelocity);
					movements.add(ms);
				}
			}
			else {
				
				double originDelta = commonVelocities.get(commonVelocities.size() - 1);
				int endPoint = this.getCurrentEndIndex();
				
				for(int j = 0; j < cnt; j++) {
					for(int i = this.getLastStartIndex(); i <= endPoint; i++) {
						AbstractMovement[] ms = movements.get(i);
						
						commonVelocities.add(originDelta);
						movements.add(ms);
					}
				}
			}
		}
		else {
			
			int endPoint = this.getCurrentEndIndex();
			double originDelta = commonVelocities.get(commonVelocities.size() - 1);
			
			for(int i = 0; i < cnt * 2 - 1; i++) {
				if(!isStartedPoint) {
					this.reverse();
				}
				else {
					
					for(int j = this.getLastStartIndex(); j <= endPoint; j++) {
						AbstractMovement[] ms = movements.get(j);
						int msSize = ms.length;
						
						AbstractMovement[] rMs = new AbstractMovement[msSize];
						
						for(int k = 0; k < msSize; k++) {
							if(i % 2 == 0)
								rMs[k] = ms[k].reverseClone();
							else
								rMs[k] = ms[k].clone();
						}
						
						movements.add(rMs);
						commonVelocities.add(originDelta);
					}
				}
			}
		}
		
		return this;
	}
	
	// Magic, Do not touch it.
	
	public Animator reverse() {
		double originDelta = commonVelocities.get(commonVelocities.size() - 1);
		
		if(!isStartedPoint) {
			AbstractMovement[] ms = movements.get(movements.size() - 1);
			int msSize = ms.length;
			
			AbstractMovement[] rMs = new AbstractMovement[msSize];
			
			for(int i = 0; i < msSize; i++) {
				rMs[i] = ms[i].reverseClone();
			}
			
			movements.add(rMs);
			commonVelocities.add(originDelta);
		}
		else {
			int endPoint = this.getCurrentEndIndex();
			
			for(int i = this.getLastStartIndex(); i < endPoint; i++) {
				AbstractMovement[] ms = movements.get(movements.size() - 1);
				int msSize = ms.length;
				
				AbstractMovement[] rMs = new AbstractMovement[msSize];
				
				for(int j = 0; j < msSize; j++) {
					rMs[j] = ms[j].reverseClone();
				}
				
				movements.add(rMs);
				commonVelocities.add(originDelta);
			}
		}
		
		return this;
	}
	
	public void commit() {
		// set none velocity
		for(int i = 0; i < movements.size(); i++) {
			AbstractMovement[] ms = movements.get(i);
			ArrayList<AbstractMovement> processedTypeMovements = new ArrayList<AbstractMovement>();
			
			for(AbstractMovement m : ms) {
				
				int deltaSum = 0;
				
				boolean isProcessed = false; 
				
				for( AbstractMovement p_m : processedTypeMovements) {
					if(m.isSameType(p_m))
						isProcessed = true;
				}
				
				if(isProcessed) // ban multiple
					continue;
				
				processedTypeMovements.add(m);
				
				ArrayList<AbstractMovement> noneVelocitySameTypeMs = new ArrayList<AbstractMovement>();
				ArrayList<AbstractMovement> sameTypeMs = new ArrayList<AbstractMovement>();
				
				for(AbstractMovement otherM : ms) {
					
					if(m.isSameType(otherM)) {
						if(!otherM.hasDeltaSecond()) {
							noneVelocitySameTypeMs.add(otherM);
						}
						else
							deltaSum += m.getDeltaSecond();
						
						sameTypeMs.add(otherM);
					}
				}
				
				for(AbstractMovement other_ms : noneVelocitySameTypeMs) {
					other_ms.setDeltaSecond((this.commonVelocities.get(i) - (double)deltaSum) / (double)noneVelocitySameTypeMs.size());
				}
				
				double sumFrontDelay = 0.0f;
				double commonDelta = this.commonVelocities.get(i);
				
				for(AbstractMovement sameM: sameTypeMs) {
					sameM.setFrontDelay(sumFrontDelay);
					sumFrontDelay += sameM.getDeltaSecond();
					
					if(sumFrontDelay < commonDelta)
						sameM.setBackDelay(commonDelta - sumFrontDelay);
				}
			}
		}
		
		currentMoveIndex = 0;
	}
	
	public void getNextFrame() {
		if(this.movements == null) {
			System.err.println("Animation not committed");
			return;
		}
		else if(this.movements.size() <= this.currentMoveIndex) { //out of index
			return;
		}
		
		AbstractMovement[] ms = this.movements.get(currentMoveIndex);
		double commonVelocity = this.commonVelocities.get(currentMoveIndex);
		
		for(int i = 0; i < ms.length; i++) {
			ms[i].move((double)currentFrame / this.fps / commonVelocity);
		}
		
		currentFrame++;
		
		if(currentFrame > this.fps * commonVelocity) {
			currentFrame = 0;
			currentMoveIndex++;
			
			if(this.movements.size() <= this.currentMoveIndex) {
				if(this.infRepeat) {
					this.currentMoveIndex = 0;
					this.currentFrame = 0;
				}
				else {
					return;
				}
			}
			
			AbstractMovement[] newMs = this.movements.get(currentMoveIndex);
			for(int i = 0; i < newMs.length; i++) {
				newMs[i].readyAnimation();
			}
		}
	}
}