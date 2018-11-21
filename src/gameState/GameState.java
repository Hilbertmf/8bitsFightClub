package gameState;

public abstract class GameState {

	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D graphics);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);
}
