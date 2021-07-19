package GameState;

public class ExampleState extends GameState {

	public ExampleState() {
		super();

		readEntitiesFromFile("exampleState");
	}

	@Override
	public void init() {}
}
