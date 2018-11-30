package gameState;
import java.awt.*;
import java.awt.event.*;
import tileMap.*;
import main.GamePanel;

public class EntryState extends GameState {
	
	private Background background;
	
	private int currentChoice;
	private String[] options = {
			"Start",
			"Help",
			"Quit"
	};
	
	
	public EntryState(GameStateManager gsm) {
		
		this.gsm = gsm;
		init();
		currentChoice = 0;
		
	}
	
	
	public void init() {
		
		try {
			
			background = new Background("resources/backgrounds/banner.jpg", 1);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		background.update();
	}
	public void draw(Graphics2D graphics) {
		
		// set background
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, 186);
		background.draw(graphics);
		
		//draw menu options
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 186, GamePanel.WIDTH, GamePanel.HEIGHT - 186);
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
			gsm.setCurrentState(GameStateManager.CHARACTERSELECTSTATE);
		}
		if(currentChoice == 1) {
			//help
			Help.shouldReturnToEntry = true;
			gsm.setCurrentState(GameStateManager.HELP);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ENTER) {
			select();
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
