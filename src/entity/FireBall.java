package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class FireBall extends Entity {
	
	private boolean hasHit;
	private boolean shouldRemove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public FireBall(double floor, boolean isRight) {
		
		super(floor);
		isFacingRight = isRight;
		moveSpeed = 3.8;
		if(isRight) dx = moveSpeed;
		else dx = -moveSpeed;
		
		width = 30;
		height = 30;
		collisionWidth = 14;
		collisionHeight = 14;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/sprites/player/fireball.gif"));
			 
			sprites = new BufferedImage[4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
			
			hitSprites = new BufferedImage[3];
			for (int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(i * width, height, width, height);
			}
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(40);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// gets called to figure out whether or not the fireball has hit something
	public void setHit() {
		if(hasHit) return;
		
		hasHit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		dx = 0;
	}
	
	public boolean shouldRemove() { return shouldRemove; }
	
	public void update() {
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dx == 0 && !hasHit) {
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
