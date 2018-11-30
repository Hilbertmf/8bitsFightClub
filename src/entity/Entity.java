package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;
import tileMap.Tile;
import tileMap.TileMap;

public abstract class Entity {

	// position n' vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	// dimensions
	protected int width;
	protected int height;
	
	// collision box
	protected int collisionWidth;
	protected int collisionHeight;
	
	// collision stuff
	protected int currentRow;
	protected int currentCol;
	protected double floor;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottom;
	protected boolean roof;
	protected boolean leftFrontier;
	protected boolean rightFrontier;
	
//	protected boolean bottomLeft;
//	protected boolean bottomRight;
	
	// player stuff
	protected int health;
	protected int maxHealth;
	protected boolean isDead;
	protected boolean isFlinching;
	protected long flinchTimer;
	protected boolean isSliding;
	protected boolean isGliding;
	
	
	// shooting
	protected boolean isShooting;
	protected int shootDamage;
	
	// punching
	protected boolean isPunching;
	protected int punchDamage;
	protected int punchRange;

	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean isFacingRight;
	
	// movement
	protected boolean isLeft;
	protected boolean isRight;
	protected boolean isUp;
	protected boolean isDown;
	protected boolean isJumping;
	protected boolean isFalling;
	
	// movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	protected double slideSpeed;
	
	// constructor
	public Entity(double floor) {
		
		this.floor = floor;
		isFalling = false;
		isJumping = false;
	//	this.tileMap = tileMap;
	//	tileSize = tileMap.getTileSize();
		
	}
	
	// checks whether or not it intersects with another entity
	public boolean intersects(Entity entity) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = entity.getRectangle();
		return r1.intersects(r2);
	}
	public Rectangle getRectangle() {
		return new Rectangle((int)x - collisionWidth, (int)y - collisionHeight, collisionWidth, collisionHeight);
	}
	
	public void calculateCorners(double x, double y) {
		
		// bottomLeft is true if we are on the ground
		bottom = y >= floor;
		roof = y <= 0;
		leftFrontier = x <= 0;
		rightFrontier = x >= 290;
	}
	
	
	// checks whether we've run into a blocked tile or a normal tile
	public void checkTileMapCollision() {
		
		currentCol = (int)x;
		currentRow = (int)y;
		
		// next destination positions
		xdest = x + dx;
		ydest = y + dy;
		xtemp = x;
		ytemp = y;
		
		// calculating for the y direction
		calculateCorners(x, ydest);
		
		// going upwards
		if(roof) {
			dy = 0;
		}
		if(dy < 0) {
			// keep going upwards
			ytemp += dy;
		}
		// going downwards
		if(dy > 0) {
			// check the bottom corners
			if(bottom) {
				// stop
				dy = 0;
				// not falling anymore, we've hit the ground
				isFalling = false;
				// set the y position to just 1 px above where we landed
				ytemp = y + 2; 
			}
			else {
				// keep going downwards
				ytemp += dy;
			}
		}
		 
		// calculating for the x direction
		calculateCorners(xdest, y);
		if(leftFrontier || rightFrontier) {
			dx = 0;
		}
		// going left
		if(dx < 0) {
			
			xtemp += dx;
			
		}
		// going right
		if(dx > 0) {
			
			xtemp += dx;
		}
		
		// check if we've ran off of a cliff
		if(!isFalling) {
			calculateCorners(x, ydest + 1);
			if(!bottom) {
				isFalling = true;
			}
		}
	}
		
	
	// getters
	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCollisionWidth() { return collisionWidth; }
	public int getCollisionHeight() { return collisionHeight; }
	public double getFloor() { return floor; }
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public boolean getFacingRight() { return isFacingRight; }
	public boolean isDead() { return isDead; }
	
	public void wasHit(int damage) {
		
		if(isDead || isFlinching) return;
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) isDead = true;
		
		isFacingRight = true;
		flinchTimer = System.nanoTime();
	}
	
	// setters
	public void setFacingRight(boolean b) {
		isFacingRight = b;
	}
	public void setFloor(int floor) {	
		this.floor = floor;
	}
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setLeft(boolean b) { isLeft = b; }
	public void setRight(boolean b) { isRight = b; }
	public void setUp(boolean b) { isUp = b; }
	public void setDown(boolean b) { isDown = b; }
	public void setJumping(boolean b) { isJumping = b; }
	public void setShooting() { isShooting = true; }
	public void setPunching() {	isPunching = true; }
	public void setGliding(boolean b) { isGliding = b; }
	public void setSliding() { isSliding = true; }
	
	
	// check whether the close attack hits
	public void checkCloseAttack(Entity enemy) {
		if(isPunching) {
			if(isFacingRight) {
				if((enemy.getx()  - (enemy.getWidth() - enemy.getCollisionWidth())) > x &&
				   (enemy.getx()  - (enemy.getWidth() - enemy.getCollisionWidth())) < x + punchRange &&
				   enemy.gety() > y - height/2 &&
				   enemy.gety() < y + height/2) {
					
					enemy.wasHit(punchDamage);
				}
			}
			else {
				if((enemy.getx()  - (enemy.getWidth() - enemy.getCollisionWidth())) < x &&
				  (enemy.getx()  - (enemy.getWidth() - enemy.getCollisionWidth())) > x - punchRange &&
				   enemy.gety() > y - height/2 &&
				   enemy.gety() < y + height/2) {
					enemy.wasHit(punchDamage);
				}
			}
		}
		
	}
	
	public void update() {
		if(health <= 0 ) {
			isDead = true;
			
		}
	}
	
	public void draw(Graphics2D graphics) {
		
		if(isFacingRight) {
			graphics.drawImage(animation.getImage(),(int)x, (int)y, null);
		}
		// draws the sprite inverted to left
		else {
			graphics.drawImage(animation.getImage(),(int)(x + width), (int)y, -width, height, null);
		
		}
	}
	 
	 
	
}
