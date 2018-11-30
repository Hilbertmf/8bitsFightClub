package gameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;

import entity.*;
import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

public class PlayState extends GameState {

	private Background background;
	private double floor;
	// teste
	private HUD hud1;
	private HUD hud2;
	private Entity player1;
	private boolean isPlayer1Dragon;
	private Entity player2;
	private boolean isPlayer2Dragon;
	
	
	public PlayState(GameStateManager gsm) {
		
		this.gsm = gsm;
		init();
		
	}
	

	private void checkCharacters() {
		
		// check for player 1
		if(CharacterSelectState.player1Choice == CharacterSelectState.DRAGON) {
			player1 = new Dragon(floor);
			isPlayer1Dragon = true;
		}
		else if(CharacterSelectState.player1Choice == CharacterSelectState.MEGAMAN) {
			player1 = new Megaman(floor);
			isPlayer1Dragon = false;
		}
		else if(CharacterSelectState.player1Choice == CharacterSelectState.BATMAN) {
			player1 = new Batman(floor);
			isPlayer1Dragon = false;
		}
		else if(CharacterSelectState.player1Choice == CharacterSelectState.SPIDERMAN) {
			player1 = new Spiderman(floor);
			isPlayer1Dragon = false;
		}
		
		// check for player 2
		if(CharacterSelectState.player2Choice == CharacterSelectState.DRAGON) {
			player2 = new Dragon(floor);
			isPlayer2Dragon = true;
		}
		else if(CharacterSelectState.player2Choice == CharacterSelectState.MEGAMAN) {
			player2 = new Megaman(floor);
			isPlayer2Dragon = false;
		}
		else if(CharacterSelectState.player2Choice == CharacterSelectState.BATMAN) {
			player2 = new Batman(floor);
			isPlayer2Dragon = false;
		}
		else if(CharacterSelectState.player2Choice == CharacterSelectState.SPIDERMAN) {
			player2 = new Spiderman(floor);
			isPlayer2Dragon = false;
		}
	}
	
	public void init() {
		
		try {
			background = new Background("resources/backgrounds/background-road.png", 1);
			floor = 165;
			
			
			checkCharacters();
			
			player1.setPosition(50, 100);
			
			player2.setPosition(230, 100);
			player2.setFacingRight(false);
			
			
			hud1 = new HUD(player1);
			hud2 = new HUD(player2);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	public void update() {
		background.update();
		player1.update();
		player2.update();
		
		// attack
		player1.checkCloseAttack(player2);
		if(!player2.isDead())
			player1.checkProjectiles(player2);
		
		player2.checkCloseAttack(player1);
		if(!player1.isDead())
			player2.checkProjectiles(player1);
		
	}
	public void draw(Graphics2D graphics) {
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		background.draw(graphics);
		if(!player1.isDead()) {
			player1.draw(graphics);
		}
		if(!player2.isDead()) {
			player2.draw(graphics);
		}
		
		// draw hud
		hud1.draw(graphics);
		hud2.drawInverted(graphics);
	}
	
	
	public void keyPressed(int key) {
		
		if(key == KeyEvent.VK_ENTER) {
			gsm.setCurrentState(GameStateManager.PAUSE);
		}
		
		if(!player1.isDead()) {	
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
				player1.setShooting();
			}
			if(key == KeyEvent.VK_K) {
				player1.setPunching();
			}
			if(key == KeyEvent.VK_L) {
				if(isPlayer1Dragon)
					player1.setGliding(true);
				else
					player1.setSliding();
			}
		}
		
		if(!player2.isDead()) {
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
				player2.setShooting();	
			}
			if(key == KeyEvent.VK_NUMPAD2) {
				player2.setPunching();
			}
			if(key == KeyEvent.VK_NUMPAD3) {
				if(isPlayer2Dragon)
					player2.setGliding(true);
				else 
					player2.setSliding();
				
			}
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
		if(key == KeyEvent.VK_L) {
			if(isPlayer1Dragon)
				player1.setGliding(false);
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
			if(isPlayer2Dragon)
				player2.setGliding(false);
			
		}
	}
	
	
}
