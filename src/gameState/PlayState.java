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
	private Megaman player1;
	private Dragon player2;
	
	public PlayState(GameStateManager gsm) {
		
		this.gsm = gsm;
		init();
		
	}
	

	public void init() {
		
		try {
			background = new Background("resources/backgrounds/background-road.png", 1);
			floor = 165;
			
			player1 = new Megaman(floor);
			player1.setPosition(50, 100);
			
			player2 = new Dragon(floor);
			player2.setPosition(230, 100);
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
		
		// player 1
		if(key == KeyEvent.VK_D) {
			player1.setRight(true);
		}
		if(key == KeyEvent.VK_A) {
			player1.setLeft(true);
		}
		if(key == KeyEvent.VK_W) {
			player1.setJumping(true);
		}
		if(key == KeyEvent.VK_J) {
			player1.setPunching();
		}
		if(key == KeyEvent.VK_K) {
			player1.setShooting();
		}
		if(key == KeyEvent.VK_L) {
			player1.setSliding();
		}
		
		// player 2
		if(key == KeyEvent.VK_RIGHT) {
			player2.setRight(true);
		}
		if(key == KeyEvent.VK_LEFT) {
			player2.setLeft(true);
		}
		if(key == KeyEvent.VK_UP) {
			player2.setJumping(true);
		}
		if(key == KeyEvent.VK_NUMPAD1) {
			player2.setPunching();
		}
		if(key == KeyEvent.VK_NUMPAD2) {
			player2.setShooting();	
		}
		if(key == KeyEvent.VK_NUMPAD3) {
			player2.setGliding(true);
		}
		
	}
	public void keyReleased(int key) {
		
		
		
		// player 1
		if(key == KeyEvent.VK_D) {
			player1.setRight(false);
		}
		if(key == KeyEvent.VK_A) {
			player1.setLeft(false);
		}
		if(key == KeyEvent.VK_W) {
			player1.setJumping(false);
		}
		
		// player 2
		if(key == KeyEvent.VK_RIGHT) {
			player2.setRight(false);
		}
		if(key == KeyEvent.VK_LEFT) {
			player2.setLeft(false);
		}
		if(key == KeyEvent.VK_UP) {
			player2.setJumping(false);
		}
		if(key == KeyEvent.VK_NUMPAD3) {
			player2.setGliding(false);
		}
	}
	
	
}
