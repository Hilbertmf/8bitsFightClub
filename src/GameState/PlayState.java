package GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;

import Main.GamePanel;

public class PlayState extends GameState {

	public PlayState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	

	public void init() {
		
	}
	public void update() {
		
	}
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		graphics.setColor(Color.BLACK);
		graphics.drawString("PLAY STATE", 100, 100);
	}
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ENTER) {
			gsm.setCurrentState(GameStateManager.MENUSTATE);
		}
	}
	public void keyReleased(int key) {
		
	}
}
