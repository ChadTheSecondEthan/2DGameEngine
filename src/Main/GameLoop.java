package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

import GameState.GameState;
import GameState.ExampleState;
import Utils.Input;
import Utils.Time;

public class GameLoop implements Runnable {
	
	// target framerate for the loop
	private int targetFramerate;
	
	// graphics for the window
	private Graphics windowGraphics;
	private BufferedImage frameImage;
	private Graphics frameGraphics;

	// the total time and the time in the current state
	private Time totalTime;
	private Time stateTime;
	private float dt;
	
	// game and input variables
	private Game game;
	private Input input;

	// list of game states
	private GameState[] states;
	
	// should the loop be running?
	private boolean shouldRun;

	GameLoop(Game game) {
		
		// default framerate is 60
		targetFramerate = 60;
		
		// initialize game and input variables
		this.game = game;
		this.input = game.getInput();

		// initialize state time
		stateTime = new Time();
	}
	
	/** starts the game loop */
	void start(Graphics windowGraphics) {
		
		// set the window graphics
		this.windowGraphics = windowGraphics;
		frameImage = new BufferedImage(game.getWindowSize().width, game.getWindowSize().height, BufferedImage.TYPE_INT_RGB);
		frameGraphics = frameImage.getGraphics();
		
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
			dt = updateTime.getElapsed();
            GameState.current().update(dt);
			updateTime.reset();
			
			// update all inputs
			input.update();
			
			// draw with the window graphics
			game.getWindow().paint(frameGraphics);
            GameState.current().draw(frameGraphics);
			
			windowGraphics.drawImage(frameImage, 0, 0, game.getWindowSize().width, game.getWindowSize().height, null);
			
			// get remaining amount of time
			long targetNanos = 1000000000 / targetFramerate;
			long elapsedNanos = (long) (frameTime.getElapsed() * 1000000000);
			long sleepMillis = (targetNanos - elapsedNanos) / 1000000;
			
			// make sure no negative times are slept
			if (sleepMillis < 0) sleepMillis = 0;
			
			// sleep for that amount of time
			try {
				Thread.sleep(sleepMillis);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// reset frame timer
			frameTime.reset();
		}
		
	}

	public float deltaTime() { return dt; }
	
	/** get the amount of time since the loop started */
	public float totalTime() { return totalTime.getElapsed(); }

	/** get the amount of time the game has been in the current state */
	public float stateTime() { return stateTime.getElapsed(); }
	
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
	public BufferedImage getFrameImage() { return frameImage; }

}
