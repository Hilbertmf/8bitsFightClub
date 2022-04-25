package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Stickyweb extends Entity {
	private boolean hasHit;
	private boolean shouldRemove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public Stickyweb(double floor, boolean isRight) {
		
		super(floor);
		moveSpeed = 3.8;
		if(isRight) dx = moveSpeed;
		else dx = -moveSpeed;
		
		width = 30;
		height = 30;
		collisionWidth = 15;
		collisionHeight = 15;
		
		// load sprites
		try {
			BufferedImage spritesheet =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/sprites/player/stickyweb.png"));
			
			sprites = new BufferedImage[1];
			sprites[0] = spritesheet.getSubimage(0, 0, width, height);
			
			hitSprites = new BufferedImage[1];
			hitSprites[0] = spritesheet.getSubimage(0, height, width, height);
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(80);
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
			animation.setDelay(200);
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
			if(isRight) {
				if(!hasHit)
					graphics.drawImage(animation.getImage(),(int)x, (int)y, null);
				else
					graphics.drawImage(animation.getImage(),(int)x + 20, (int)y, null);
			}
			// draws the sprite inverted to left
			else {
				graphics.drawImage(animation.getImage(),(int)(x + width), (int)y, -width, height, null);
			
			}
		}

}
