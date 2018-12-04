package gameState;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.GamePanel;
import tileMap.Background;

public class Pause extends GameState {

	private Background background;
	private int currentChoice;
	private String[] options = {
			"Resume",
			"Menu",
			"Quit"
	};
	
	public Pause(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	
	
	public void init() {
		
	}
	public void update() {
		
	}
	
	public void select() {
		if(currentChoice == 0) {
			gsm.setCurrentState(GameStateManager.PLAYSTATE);
			if(PlayState.getPlayer1().isDead() || PlayState.getPlayer2().isDead())
				gsm.initState(GameStateManager.PLAYSTATE);
		}
		if(currentChoice == 1) {
			gsm.setCurrentState(GameStateManager.CHARACTERSELECTSTATE);
			gsm.initState(GameStateManager.CHARACTERSELECTSTATE);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void draw(java.awt.Graphics2D graphics) {
	
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
		
		// draw options
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 175, GamePanel.WIDTH, GamePanel.HEIGHT - 165);
		
		for (int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				graphics.setColor(Color.WHITE);
			}
			else {
				graphics.setColor(Color.RED);
			}
			graphics.drawString(options[i], 120, 190 + i * 15);
		}
		
	}
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ENTER) {
			select();
		}
		if(key == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(key == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int key) {
		
	}
}
