package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class CharacterSelectState extends GameState {
	
	private static int currentChoice;
	private boolean isPlayer1Turn;
	private String[] options = {
			"DRAGON",
			"MEGAMAN",
			"BATMAN",
			"SPIDER-MAN",
			"TUTORIAL",
			"CLOSE"
	};
	
	public static final int DRAGON = 0;
	public static final int MEGAMAN = 1;
	public static final int BATMAN = 2;
	public static final int SPIDERMAN = 3;
	public static final int TUTORIAL = 4;
	public static final int EXIT = 5;
	public static int player1Choice;
	public static int player2Choice;
	
	private BufferedImage dragonModel;
	private BufferedImage megamanModel;
	private BufferedImage batmanModel;
	private BufferedImage spidermanModel;
	
	public CharacterSelectState (GameStateManager gsm) {
		this.gsm = gsm;
		init();
		
	}
	
	public void init() {
		isPlayer1Turn = true;
		currentChoice = 0;
		try {
			
			dragonModel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/sprites/player/dragonModel.png"));
			megamanModel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/sprites/player/megamanModel.png"));
			batmanModel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/sprites/player/batmanModel.png"));
			spidermanModel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/sprites/player/spidermanModel.png"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update() {
		
	}
	
	private static boolean hasHappened;
	public static boolean getHappened() { return hasHappened; }
	
	public static int getPlayer1Choice() { return player1Choice; }
	public static int getPlayer2Choice() { return player2Choice; }
	
	public void select() {
		if(currentChoice == DRAGON) {
			if(isPlayer1Turn) {
				player1Choice = DRAGON;
				isPlayer1Turn = false;
			}
			else {
				player2Choice = DRAGON;
				hasHappened = true;
				gsm.setCurrentState(GameStateManager.PLAYSTATE);
			}
		}
		if(currentChoice == MEGAMAN) {
			if(isPlayer1Turn) {
				player1Choice = MEGAMAN;
				isPlayer1Turn = false;
			}
			else {
				player2Choice = MEGAMAN;
				hasHappened = true;
				gsm.setCurrentState(GameStateManager.PLAYSTATE);
			}
		}
		if(currentChoice == BATMAN) {
			if(isPlayer1Turn) {
				player1Choice = BATMAN;
				isPlayer1Turn = false;
			}
			else {
				player2Choice = BATMAN;
				hasHappened = true;
				gsm.setCurrentState(GameStateManager.PLAYSTATE);
			}
		}
		if(currentChoice == SPIDERMAN) {
			if(isPlayer1Turn) {
				player1Choice = SPIDERMAN; 
				isPlayer1Turn = false;	
			}
			else {
				player2Choice = SPIDERMAN;
				hasHappened = true;
				gsm.setCurrentState(GameStateManager.PLAYSTATE);
			}
		}
		if(currentChoice == TUTORIAL) {
			Help.shouldReturnToEntry = false;
			gsm.setCurrentState(GameStateManager.HELP);
		}
		if(currentChoice == EXIT) {
			System.exit(0);
		}
		
	}
	
	public void draw(Graphics2D graphics) {
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		graphics.setColor(Color.RED);
		graphics.drawString("CHOOSE YOUR DESTINY", 80, 20);
		
		for (int i = 0; i < options.length; i++) {
			
			if(i == currentChoice) 
				graphics.setColor(Color.WHITE);
			else 
				graphics.setColor(Color.RED);
			
			graphics.drawString(options[i], 120, 100 + i * 15);
			
			if(currentChoice == DRAGON) {
				graphics.drawImage(dragonModel, 250, 75, null);
			}
			else if(currentChoice == MEGAMAN) {
				graphics.drawImage(megamanModel, 250, 95, null);
			}
			else if(currentChoice == BATMAN) {
				graphics.drawImage(batmanModel, 250, 110, null);
			}
			else if(currentChoice == SPIDERMAN) {
				graphics.drawImage(spidermanModel, 250, 125, null);
			}
			
			graphics.setColor(Color.RED);
			if(isPlayer1Turn)
				graphics.drawString("PLAYER 1", 120, 70);
			else
				graphics.drawString("PLAYER 2", 120, 70);
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
