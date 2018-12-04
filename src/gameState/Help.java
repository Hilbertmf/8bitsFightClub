package gameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;


import main.GamePanel;
import tileMap.Background;

public class Help  extends GameState{

	public static boolean shouldReturnToEntry;
	
	public Help(GameStateManager gsm) {
		
 		this.gsm = gsm;
		init();
		
	}
	

	public void init() {
		
		
	}
	public void update() {
		
	}
	public void draw(Graphics2D graphics) {
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		graphics.setColor(Color.BLACK);
		graphics.drawString("CONTROLS", 125, 20);
		graphics.drawString("PLAYER 1", 50, 50);
		graphics.drawString("Up: W", 50, 70);
		graphics.drawString("Left: A", 50, 90);
		graphics.drawString("Right: D", 50, 110);
		graphics.drawString("Shoot: J", 50, 130);
		graphics.drawString("Punch: K", 50, 150);
		graphics.drawString("Slide/Glide: L", 50, 170);
		
		graphics.drawString("PLAYER 2", 200, 50);
		graphics.drawString("Up: Up-arrow", 200, 70);
		graphics.drawString("Left: Left-arrow", 200, 90);
		graphics.drawString("Right: Right-arrow", 200, 110);
		graphics.drawString("Shoot: 1", 200, 130);
		graphics.drawString("Punch: 2", 200, 150);
		graphics.drawString("Slide/Glide: 3", 200, 170);
	}


	@Override
	public void keyPressed(int key) {
		
		if(key == KeyEvent.VK_ENTER) {
			if(shouldReturnToEntry)
				gsm.setCurrentState(GameStateManager.ENTRYSTATE);
			else
				gsm.setCurrentState(GameStateManager.CHARACTERSELECTSTATE);
		}
	}


	@Override
	public void keyReleased(int key) {
		// TODO Auto-generated method stub
		
	}

	
}
	
