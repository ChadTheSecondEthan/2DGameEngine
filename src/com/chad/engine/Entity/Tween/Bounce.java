package com.chad.engine.Entity.Tween;

import com.chad.engine.Entity.Entity;

public class Bounce extends Tween {

	private final float groundHeight;
	private final float fallDist;
	private final float fallAccel;

	private float vel;
	private byte bounces;
	private byte maxBounces;
	
	Bounce(Entity object, float fallDist, float fallAccel) {
		super(object);
		this.groundHeight = object.getY() + fallDist;
		this.fallDist = fallDist;
		this.fallAccel = fallAccel;
		
		vel = 0;
		bounces = 0;
		maxBounces = 4;
	}
	
	@Override
	public void update(float dt) {
		dt *= 4f;
		passed += dt;
		
		vel += fallAccel * dt;
		
		float y = object.getY() + vel * dt;
		if (y > groundHeight) {
			y = groundHeight;
			bounces++;
			vel = -(float) Math.sqrt(2 * fallAccel * fallDist / bounces);

			if (bounces == maxBounces)
				destroy();
		}
		object.setY(y);
	}
	
	public void setMaxBounces(int bounces) { maxBounces = (byte) bounces; }
	
}