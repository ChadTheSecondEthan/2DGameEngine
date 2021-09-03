package com.chad.engine;

import java.awt.*;
import javax.swing.*;

import com.chad.engine.gameState.GameState;
import com.chad.engine.utils.Keyboard;
import com.chad.engine.utils.Mouse;

public class Game {

	public static Game instance;
	
	// window, loop, input variables
	private GameLoop gameLoop;

	// has the game been started?
	private boolean started;
	
	public Game(String name) {
		instance = this;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Window.init(name, screenSize.width - 50, screenSize.height - 50);
		
		// create the game loop
		gameLoop = new GameLoop(this);

		started = false;
	}

	public void setStates(GameState... states) { gameLoop.setStates(states); }

	private void start() {
		started = true;
		Window.setVisible(true);
		gameLoop.start();
	}
	
	/** Starts the game with the game state given */
	public void start(String startState) {
		gameLoop.setState(startState);
		start();
	}

	/** Starts the game with the game state given */
	public void start(int startState) {
		gameLoop.setState(startState);
		start();
	}
	
	/** returns the game loop */
	public GameLoop getGameLoop() {
		return gameLoop;
	}

	public boolean isStarted() { return started; }

}
