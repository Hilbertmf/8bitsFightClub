package gameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;

import entity.*;
import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

public class PlayState extends GameState {

	private TileMap tileMap;
	private Background background;
	private boolean isTurnedRight;
	private double floor;
	// teste
	private Dragon player1;
	private Megaman player2;
	
	public PlayState(GameStateManager gsm) {
		
		this.gsm = gsm;
		init();
		
	}
	

	public void init() {
		
		try {
			background = new Background("Resources/Backgrounds/background-road.png", 1);
			floor = 165;
			
			player1 = new Dragon(floor);
			player1.setPosition(50, 100);
			
			player2 = new Megaman(floor);
			player2.setPosition(150, 100);
			player2.setFacingRight(false);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	public void update() {
		background.update();
		player1.update();
		player2.update();
	}
	public void draw(Graphics2D graphics) {
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		background.draw(graphics);
		player1.draw(graphics);
		player2.draw(graphics);
		
	}
	public void keyPressed(int key) {
		
		if(key == KeyEvent.VK_ENTER) {
			gsm.setCurrentState(GameStateManager.ENTRYSTATE);
		}
		if(key == KeyEvent.VK_RIGHT) {
			player1.setRight(true);
		}
		if(key == KeyEvent.VK_LEFT) {
			player1.setLeft(true);
		}
		if(key == KeyEvent.VK_UP) {
			player1.setJumping(true);
		}
		// scratching
		if(key == KeyEvent.VK_SPACE) {
			player1.setPunching();
		}
		if(key == KeyEvent.VK_Z) {
			player1.setShooting();	
		}
		if(key == KeyEvent.VK_X) {
			player1.setGliding(true);
		}
		if(key == KeyEvent.VK_D) {
			player2.setRight(true);
		}
		if(key == KeyEvent.VK_A) {
			player2.setLeft(true);
		}
		if(key == KeyEvent.VK_W) {
			player2.setJumping(true);
		}
		
	}
	public void keyReleased(int key) {
		if(key == KeyEvent.VK_RIGHT) {
			player1.setRight(false);
		}
		if(key == KeyEvent.VK_LEFT) {
			player1.setLeft(false);
		}
		if(key == KeyEvent.VK_UP) {
			player1.setJumping(false);
		}
		// scratching
		if(key == KeyEvent.VK_SPACE) {
			
		}
		if(key == KeyEvent.VK_Z) {
			
			
		}
		if(key == KeyEvent.VK_X) {
			player1.setGliding(false);
		}
		if(key == KeyEvent.VK_D) {
			player2.setRight(false);
		}
		if(key == KeyEvent.VK_A) {
			player2.setLeft(false);
		}
		if(key == KeyEvent.VK_W) {
			player2.setJumping(false);
		}
	}
	
	
}
