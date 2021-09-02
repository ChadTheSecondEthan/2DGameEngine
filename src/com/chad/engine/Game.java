package com.chad.engine;

import java.awt.*;
import javax.swing.*;

import com.chad.engine.gameState.GameState;
import com.chad.engine.utils.Keyboard;
import com.chad.engine.utils.Mouse;

public class Game {

	public static Game instance;
	
	// window, loop, input variables
	private JFrame window;
	private GameLoop gameLoop;

	// the starting size of the window
	private Dimension startSize;

	// has the game been started?
	private boolean started;
	
	public Game(String name) {
		instance = this;
		
		// create a new window
		window = new JFrame(name);
		
		// set up some basic stuff
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(screenSize.width - 50, screenSize.height - 50);
		startSize = window.getSize();
		
		// center the window
		window.setLocationRelativeTo(null);
		
		// add listeners
		window.addKeyListener(Keyboard.getListener());
		window.addMouseListener(Mouse.getMouseListener());
		window.addMouseWheelListener(Mouse.getMouseWheelListener());
		window.addMouseMotionListener(Mouse.getMouseMotionListener());
		window.setBackground(Color.white);
		
		// create the game loop
		gameLoop = new GameLoop(this);

		started = false;
	}

	public void setStates(GameState... states) { gameLoop.setStates(states); }
	
	/** Starts the game with the game state given */
	public void start(String startState) {

		started = true;
		
		// show the window
		window.setVisible(true);
		
		// start the game loop
		gameLoop.setState(startState);
		gameLoop.start(window.getGraphics());
	}

	/** Starts the game with the game state given */
	public void start(int startState) {

		started = true;

		// show the window
		window.setVisible(true);

		// start the game loop
		gameLoop.setState(startState);
		gameLoop.start(window.getGraphics());
	}

	/** the starting size of the window */
	public Dimension getStartSize() { return startSize; }

	/**
	 * returns a percent of the height of the start size of the window
	 * @param percent a value between 0 and 1
	 */
	public float percentHeight(float percent) {
		return startSize.height * percent;
	}

	/**
	 * returns a percent of the width of the start size of the window
	 * @param percent a value between 0 and 1
	 */
	public float percentWidth(float percent) {
		return startSize.width * percent;
	}
	
	/** returns the game window */
	public JFrame getWindow() {
		return window;
	}
	
	/** returns the current size of the window */
	public Dimension getWindowSize() {
		return window.getSize();
	}
	
	/** returns the game loop */
	public GameLoop getGameLoop() {
		return gameLoop;
	}

	public boolean isStarted() { return started; }

}
