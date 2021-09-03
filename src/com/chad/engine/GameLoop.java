package com.chad.engine;

import com.chad.engine.gameState.GameState;
import com.chad.engine.utils.Keyboard;
import com.chad.engine.utils.Mouse;
import com.chad.engine.utils.Time;

import static com.chad.engine.Global.stateTime;
import static com.chad.engine.Global.totalTime;

public class GameLoop implements Runnable {

	// list of game states
	private GameState[] states;
	
	// should the loop be running?
	private boolean shouldRun;
	
	/** starts the game loop */
	void start() {
		// create the thread for the game loop
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		
		shouldRun = true;
		
		// initialize times
		Time updateTime = new Time();
		Time frameTime = new Time();
		totalTime = new Time();
		stateTime.reset();

		while (shouldRun) {
			
			// update and reset timer
			Global.dt = updateTime.getElapsed();
            GameState.current().update(Global.dt);
			updateTime.reset();
			
			// update all inputs
			Keyboard.update();
			Mouse.update();
			
			// draw with the window graphics
            GameState.current().draw();
            Window.draw();
			
			// get remaining amount of time
			long targetNanos = 1000000000 / Global.fps;
			long elapsedNanos = (long) (frameTime.getElapsed() * 1000000000);
			long sleepMillis = (targetNanos - elapsedNanos) / 1000000;
			
			// sleep for that amount of time
			try {
				Thread.sleep(sleepMillis < 0 ? 0 : sleepMillis);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// reset frame timer
			frameTime.reset();
		}
	}

	/** stops the game loop */
	public void stop() { shouldRun = false; }
	
	/** set the current state */
	public void setState(String stateName) {
		if (GameState.current() != null)
            GameState.current().invokeListeners(GameState.ON_STATE_CHANGE);

		for (GameState state : states)
			if (state.getClass().getSimpleName().equals(stateName)) {
				GameState.setCurrent(state);
				state.init();
				return;
			}

		stateTime.reset();
	}

	public void setState(int index) {
		if (GameState.current() != null)
			GameState.current().invokeListeners(GameState.ON_STATE_CHANGE);
		GameState.setCurrent(states[index]);
		GameState.current().init();
		stateTime.reset();
	}

	public void setStates(GameState[] states) { this.states = states; }

}
