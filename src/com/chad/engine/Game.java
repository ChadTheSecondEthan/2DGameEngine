package com.chad.engine;

import java.awt.*;
import javax.swing.*;

import com.chad.engine.gameState.GameState;
import com.chad.engine.utils.Keyboard;
import com.chad.engine.utils.Mouse;

public class Game {
	
	// window, loop, input variables
	private static GameLoop gameLoop;

	// has the game been started?
	public static boolean started;
	
	public static void init(String title) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Window.init(title, screenSize.width - 50, screenSize.height - 50);
		
		// create the game loop
		gameLoop = new GameLoop();

		started = false;
	}

	public static void setState(String name) { gameLoop.setState(name); }
	public static void setState(int index) { gameLoop.setState(index);}
	public static void setStates(GameState... states) { gameLoop.setStates(states); }

	private static void start() {
		started = true;
		Window.setVisible(true);
		gameLoop.start();
	}
	
	/** Starts the game with the game state given */
	public static void start(String startState) {
		if (started)
			throw new Error("Game started already");
		gameLoop.setState(startState);
		start();
	}

	/** Starts the game with the game state given */
	public static void start(int startState) {
		if (started)
			throw new Error("Game started already");
		gameLoop.setState(startState);
		start();
	}

}
