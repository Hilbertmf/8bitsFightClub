package entity;

import tileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class Dragon extends Entity {
	
	private ArrayList<FireBall> fireBalls;
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2, 8, 1, 2, 2, 5, 4
	};
	
	
	// animation actions
	private static final int IDLE = 0; 
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int SHOOTING = 4;
	private static final int PUNCHING = 5;
	private static final int GLIDING = 6;
	private static final int FLINCHING = 7;
	
	public Dragon(double floor) {
		
		super(floor);
		
		width = 30;
		height = 30;
		collisionWidth = 20;
		collisionHeight = 20;
		
		moveSpeed = 0.25;
		maxSpeed = 2.0;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		isFacingRight = true;
		
		health = maxHealth = 50;
		
		shootDamage = 2;
		fireBalls = new ArrayList<FireBall>();
		punchDamage = 4;
		punchRange = 40;
		
		// load sprites
		try {
			
			BufferedImage spriteSheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/sprites/player/dragonSprites.gif"));
			
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 7; i++) {
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					if(i != PUNCHING){
						bi[j] = spriteSheet.getSubimage(j * width, i * height, width, height);
					} 
					else
						bi[j] = spriteSheet.getSubimage(j * width * 2, i * height, width * 2, height); 
				}
				
				sprites.add(bi);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
	}
	
	
	// getters
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	
	// setters
	public void setShooting() {
		isShooting = true;
	}
	public void setPunching() {
		isPunching = true;
	}
	
	
	
	
	public void checkProjectiles(Entity enemy) {
		// shoots
		// checks if we are hitting the enemy
		for (int i = 0; i < fireBalls.size(); i++) {
			if(fireBalls.get(i).intersects(enemy)) {
				fireBalls.get(i).setHit();
				enemy.wasHit(shootDamage);
				
			}
		}
		
	}
	
	public void checkAttack(Entity enemy) {
		
		this.checkProjectiles(enemy);
		super.checkCloseAttack(enemy);
	}
	
	// this function determines where the next position of the player is by reading keyboard input
	private void getNextPosition() {
		
		// movement
		if(isLeft) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(isRight) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		// cannot move while attacking unless in the air yet
		if((currentAction == PUNCHING || currentAction == SHOOTING) && !(isJumping || isFalling)) {
			// cannot move;
			dx = 0;
		}
		
		// jumping
		if(isJumping && !isFalling) {
			dy = jumpStart;
			isFalling = true;
		}
		
		// falling
		if(isFalling) {
			if(isGliding) {
				dy += 0.1 * fallSpeed;
				if(dy > maxFallSpeed * 0.1) dy = maxFallSpeed * 0.1;
			}
			else {
				dy += fallSpeed;
				
				if(dy > 0) isJumping = false;
				// this makes the longer you hold the jump button the higher you'll jump
				if(dy < 0 && !isJumping) dy += stopJumpSpeed;
				if(dy > maxFallSpeed) dy = maxFallSpeed;
			}
		}
		
	}
	
	public void update() {
		
		// update position
		
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		
		// check attacks
		if(currentAction == PUNCHING) {
			if(animation.hasPlayedOnce()) isPunching = false;
		}
		else if(currentAction == SHOOTING) {
			if(animation.hasPlayedOnce()) isShooting = false;
		}
		
		// fireball attack and fix exploit of spamming shots
		if(isShooting && currentAction != SHOOTING && !isPunching) {
			FireBall fb = new FireBall(floor, isFacingRight);
			fb.setPosition(x, y);
			fireBalls.add(fb);
		}
		
		// update fireballs
		for (int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).update();
			if(fireBalls.get(i).shouldRemove()) {
				fireBalls.remove(i);
				i--;
			}
		}
		
		// fix bug of spamming shots
		if(isPunching && isShooting) {
			isShooting = false;
		}
		// set animation
		if(isPunching) {
			if(currentAction != PUNCHING) {
				currentAction = PUNCHING;
				animation.setFrames(sprites.get(PUNCHING));
				animation.setDelay(50);
				width = 60;
			}
		}
		
		else if(isShooting) {
			if(currentAction != SHOOTING) {
				currentAction = SHOOTING;
				animation.setFrames(sprites.get(SHOOTING));
				animation.setDelay(300);
				width = 30;
			}
		}
		else if(isGliding) {
			if(currentAction != GLIDING) {
				currentAction = GLIDING;
				animation.setFrames(sprites.get(GLIDING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy > 0) {
			if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		
		}
		else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(isLeft || isRight) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}
		
		animation.update();
		
		// set direction
		// the player currently cannot move while attacking 
	
		if(isRight) isFacingRight = true;
		if(isLeft) isFacingRight = false;
		super.update();
	}
	
	
	public void draw(Graphics2D graphics) {
		
		// draw fireballs
		for (int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(graphics);	
		}
		
		// flinching mechanic that blinks the player when he takes damage
		if(isFlinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		// draw player
		super.draw(graphics);
	
	}
}
