package Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GameState.GameState;
import Utils.Input;

public class Game implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	// window, loop, input variables
	private JFrame window;
	private GameLoop gameLoop;
	private Input input;
	
	public Game(String name) {
		
		// create a new window
		window = new JFrame(name);
		
		// set up some basic stuff
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(screenSize.width - 50, screenSize.height - 50);
		
		// center the window
		window.setLocationRelativeTo(null);
		
		// add listeners
		window.addKeyListener(this);
		window.addMouseListener(this);
		window.addMouseWheelListener(this);
		window.addMouseMotionListener(this);
		
		// create an input variable
		input = new Input();
		
		// create the game loop
		gameLoop = new GameLoop(this);
	}
	
	/** Starts the game with the game state given */
	public void start(GameState startState) {
		
		// show the window
		window.setVisible(true);
		
		// start the game loop
		gameLoop.setState(startState);
		gameLoop.start(window.getGraphics());
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
		input.onMouseScroll(e.getScrollAmount());
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
