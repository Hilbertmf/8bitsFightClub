package TileMap;


import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import Main.Game;
import Main.GamePanel;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;


public class Background {
	
	public BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double moveScale;
	
	
	public Background(String str, double ms) {
		
		try {
			
			image = ImageIO.read(new FileInputStream(str));
			moveScale = ms;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	public void draw(Graphics2D graphics) {
		graphics.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			graphics.drawImage(image, (int)x + GamePanel.WIDTH, (int)y, null);
		}
		
		if(x > 0) {
			graphics.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
			
		}
	}
	
	public void infiniteScrolling(Graphics2D graphics) {
		graphics.drawImage(image, (int)x, (int)y, null);
		if(x > 0) {
			graphics.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
			
		}
		if(x == GamePanel.WIDTH)
			x = 0;
	}
	
}
