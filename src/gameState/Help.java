package gameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;


import main.GamePanel;
import tileMap.Background;

public class Help  extends GameState{

	private Background background;
	
	public Help(GameStateManager gsm) {
		
		this.gsm = gsm;
		init();
		
	}
	

	public void init() {
		
		//tileMap = new TileMap(30);
		
	}
	public void update() {
		
	}
	public void draw(Graphics2D graphics) {
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		graphics.setColor(Color.BLACK);
		graphics.drawString("SO DEUS AJUDA", 100, 100);
	}


	@Override
	public void keyPressed(int key) {
		
		if(key == KeyEvent.VK_ENTER) {
			gsm.setCurrentState(GameStateManager.ENTRYSTATE);
		}
	}


	@Override
	public void keyReleased(int key) {
		// TODO Auto-generated method stub
		
	}

}
	
