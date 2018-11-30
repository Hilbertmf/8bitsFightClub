package entity;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class HUD {
	
	private Entity player;
	private BufferedImage image;
	private Font font;
	private int width;
	private int height;
	
	public HUD(Entity e) {
		player = e;
		width = 80;
		height = 40;
		try {
			
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/HUD/hud.png"));
			font = new Font("Arial", font.PLAIN, 14);
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void draw(Graphics2D graphics) {
		graphics.drawImage(image, 0, 30, null);
		graphics.setFont(font);
		graphics.drawString(player.getHealth() + "/" + player.getMaxHealth(), 15, 45);
	}
	public void drawInverted(Graphics2D graphics) {
		graphics.drawImage(image, GamePanel.WIDTH, 30, -width, height, null);
		graphics.setFont(font);
		graphics.drawString(player.getHealth() + "/" + player.getMaxHealth(), GamePanel.WIDTH - 70, 45);
	}
}
