package com.chad.engine.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.chad.engine.gameState.GameState;
import com.chad.engine.utils.Input;

public class Game implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public static Game instance;
	
	// window, loop, input variables
	private JFrame window;
	private GameLoop gameLoop;
	private Input input;

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
		window.addKeyListener(this);
		window.addMouseListener(this);
		window.addMouseWheelListener(this);
		window.addMouseMotionListener(this);
		window.setBackground(Color.white);
		
		// create an input variable
		input = new Input();
		
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
	
	/** returns the input variable */
	public Input getInput() {
		return input;
	}

	public boolean isStarted() { return started; }
	
	/* 
	 * Key and Mouse methods. For each one, I just send the info 
	 * to the Input class for handling
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		input.keyPressed(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		input.keyReleased(e.getKeyCode());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		input.onMouseScroll(e.getScrollAmount() * e.getWheelRotation());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		input.onMouseClick();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		input.onMousePress();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		input.onMouseRelease();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		input.onMouseMove(e.getPoint());
	}
	
	/*
	 * Unused mouse methods
	 */

	@Override 
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}

}
