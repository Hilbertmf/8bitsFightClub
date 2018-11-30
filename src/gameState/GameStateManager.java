package gameState;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int ENTRYSTATE = 0;
	public static final int PLAYSTATE = 1;
	public static final int HELP = 2;
	public static final int PAUSE = 3;
	public static final int CHARACTERSELECTSTATE = 4;
	
	public GameStateManager() {
		
		gameStates = new ArrayList<GameState>();
		
		currentState = ENTRYSTATE;
		gameStates.add(new EntryState(this));
		gameStates.add(new PlayState(this));
		gameStates.add(new Help(this));
		gameStates.add(new Pause(this));
		gameStates.add(new CharacterSelectState(this));
		
	}
	
	public void setCurrentState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D graphics) {
		gameStates.get(currentState).draw(graphics);
	}
	
	public void keyPressed(int key) {
		gameStates.get(currentState).keyPressed(key);
	}
	
	public void keyReleased(int key) {
		gameStates.get(currentState).keyReleased(key);
	}
}
