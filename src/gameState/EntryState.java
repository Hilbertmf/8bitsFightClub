package gameState;
import java.awt.*;
import java.awt.event.*;

import main.GamePanel;
import tileMap.Background;

public class EntryState extends GameState {
	
	private Background background;
	
	private int currentChoice = 0;
	private String[] options = {
			"Start",
			"Help",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public EntryState(GameStateManager gsm) {
		
		this.gsm = gsm;
		try {

			background = new Background("Resources/Backgrounds/banner.jpg", 1);
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 12);
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void init() {
		
	}
	public void update() {
		background.update();
	}
	public void draw(Graphics2D graphics) {
		
		// set background
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, 186);
		background.infiniteScrolling(graphics);
		// draw title
		graphics.setColor(titleColor);
		graphics.setFont(titleFont);
		//graphics.drawString("Joguinho", 80, 70);
		
		//draw menu options
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 186, GamePanel.WIDTH, GamePanel.HEIGHT - 186);
		graphics.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				graphics.setColor(Color.WHITE);
			}
			else {
				graphics.setColor(Color.RED);
			}
			graphics.drawString(options[i], 140, 200 + i * 15);
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			//start
			gsm.setCurrentState(GameStateManager.PLAYSTATE);
		}
		if(currentChoice == 1) {
			//help
			gsm.setCurrentState(GameStateManager.HELP);
			
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ENTER) {
			select();
			//gsm.setCurrentState(GameStateManager.PLAYSTATE);
		}
		if(key == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1)
				currentChoice = options.length - 1;
		}
		if(key == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length)
				currentChoice = 0;
		}
	}
	public void keyReleased(int key) {
		
	}
}
