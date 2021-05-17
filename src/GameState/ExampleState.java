package GameState;

import Main.GameLoop;
import UI.*;

public class ExampleState extends GameState {

	public ExampleState(GameLoop gameLoop) {
		super(gameLoop);

		// spawn a button
		Button button = new Button(this);
		spawn(button);
		button.setPosition(200, 200);

		// spawn a text
		Text text = new Text(this, "Hi there");
		spawn(text);
		text.setPosition(400, 400);
	}

}
