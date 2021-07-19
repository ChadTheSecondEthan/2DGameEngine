package Entity.Tween;

import Entity.Entity;
import GameState.GameState;
import Utils.Mathf;

public abstract class Tween extends Entity {
	
	protected Entity object;
	protected float passed;

	Tween(Entity object) {
		super();

		this.object = object;
		passed = 0;
	}

	/**
	 * Moves the object on the y axis to the given value over the given time
	 * @param to the x value to go to
	 * @param t the time to pass
	 */
	public static Move moveX(Entity object, float to, float t) {
		return new Move(object, to, t, true);
	}
	
	/**
	 * Moves the object on the y axis to the given value over the given time
	 * @param to the y value to go to
	 * @param t the time to pass
	 */
	public static Move moveY(Entity object, float to, float t) {
		return new Move(object, to, t, false);
	}
	
	/**
	 * Oscillates the object on the x axis according to the function
	 * x(t) = amp * sin(2π * freq * t + offset) + x
	 * @param amp the height of the oscillation
	 * @param freq the frequency of the oscillation (times per second)
	 * @param offset the offset of the oscillation (or the phase constant)
	 */
	public static Oscillate oscillateX(Entity object, float amp, float freq, float offset) {
		return new Oscillate(object, amp, freq, offset, true);
	}
	
	/**
	 * Oscillates the object on the y axis according to the function
	 * y(t) = amp * sin(2π * freq * t + offset) + y
	 * @param amp the height of the oscillation
	 * @param freq the frequency of the oscillation (times per second)
	 * @param offset the offset of the oscillation (or the phase constant)
	 */
	public static Oscillate oscillateY(Entity object, float amp, float freq, float offset) {
		return new Oscillate(object, amp, freq, offset, false);
	}
	
	/**
	 * Bounces the object up and down
	 * 50 and 100 are good values
	 * 
	 * @param fallDist the amount to fall before the bounces
	 * @param fallAccel basically gravity
	 */
	public static Bounce bounce(Entity object, float fallDist, float fallAccel) {
		return new Bounce(object, fallDist, fallAccel);
	}
}

class Move extends Tween {
	
	private float to;
	private float start;
	private float t;
	private boolean x;
	
	Move(Entity object, float to, float t, boolean x) {
		super(object);
		this.to = to;
		this.t = t;
		this.x = x;
		start = x ? object.getX() : object.getY();
	}
	
	@Override
	public void update(float dt) {
		passed += dt * t;

		// move to the specified position based on the time passed
		float cur = Mathf.lerp(start, to, passed);
		if (x)
			object.setX(cur);
		else
			object.setY(cur);

		// destroy if the passed time is greater than
		// the specified time
		if (passed >= t)
			destroy();
	}
	
}

class Oscillate extends Tween {
	
	private float amp;
	private float start;
	private float freq;
	private float offset;
	private boolean x;
	
	Oscillate(Entity object, float amp, float freq, float offset, boolean x) {
		super(object);
		this.amp = amp;
		start = x ? object.getX() : object.getY();
		this.freq = freq;
		this.offset = offset;
		this.x = x;
	}
	
	@Override
	public void update(float dt) {
		passed += dt;
		
		float cur = (float) (amp * Math.sin(2 * Math.PI * freq * passed + offset)) + start;
		
		if (x) object.setX(cur);
		else object.setY(cur);
	}
	
}