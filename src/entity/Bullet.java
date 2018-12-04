package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bullet extends Entity {

	private boolean hasHit;
	private boolean shouldRemove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public Bullet(double floor, boolean isRight) {
		
		super(floor);
		moveSpeed = 5.8;
		if(isRight) dx = moveSpeed;
		else dx = -moveSpeed;
		
		width = 30;
		height = 30;
		collisionWidth = 10;
		collisionHeight = 10;
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/sprites/player/bullet.png"));			
			sprites = new BufferedImage[2];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
			
			hitSprites = new BufferedImage[1];
			hitSprites[0] = spritesheet.getSubimage(0, height, width, height);
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(50);
 		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// gets called to figure out whether or not the bullet has hit something
		public void setHit() {
			if(hasHit) return;
			
			hasHit = true;
			animation.setFrames(hitSprites);
			animation.setDelay(100);
			dx = 0;
		}
		
		public boolean shouldRemove() { return shouldRemove; }
		
		public void update() {
			
			checkTileMapCollision();
			setPosition(xtemp, ytemp);
			
			if(dx == 0 & !hasHit) {
				setHit();
			}
			
			animation.update();
			if(hasHit && animation.hasPlayedOnce()) {
				shouldRemove = true;
			}
		}
		
		public void draw(Graphics2D graphics) {
			super.draw(graphics);
		}
	
}
