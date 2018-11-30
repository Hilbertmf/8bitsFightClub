package tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;
import main.GamePanel;

public class Background {
	
	public BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double moveScale;
	
	
	public Background(String path, double ms) {
		
		try {
			InputStream in =  getClass().getClassLoader().getResourceAsStream(path);
			if(in == null) System.out.println("NULL");
			
			image = ImageIO.read(in);
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
	
	
}
