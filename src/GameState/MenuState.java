package GameState;
import java.awt.*;
import java.awt.event.*;

import TileMap.Background;
import Main.GamePanel;

public class MenuState extends GameState {
	
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
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		try {

			
			background = new Background("Resources/Backgrounds/logo.jpg", 1);
			background.setVector(0.5,  0);
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
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, 186);
		//background.draw(graphics);
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
		
		/*graphics.setColor(Color.BLUE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		graphics.setColor(Color.BLACK);
		graphics.drawString("MENU STATE", 100, 100);*/
	}
	
	private void select() {
		if(currentChoice == 0) {
			//start
		}
		if(currentChoice == 1) {
			//help
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ENTER) {
			select();
			gsm.setCurrentState(GameStateManager.PLAYSTATE);
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
