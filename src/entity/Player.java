package entity;

public abstract class Player extends Entity {

	// player stuff
	protected int health;
	protected int maxHealth;
	protected boolean isDead;
	protected boolean isFlinching;
	protected long flinchTimer;
	
	// shooting
	protected boolean isShooting;
	protected int shootDamage;
	
	// punching
	protected boolean isPunching;
	protected int punchDamage;
	protected int punchRange;
	
	// animation actions
	protected static final int IDLE = 0; 
	protected static final int WALKING = 1;
	protected static final int JUMPING = 2;
	protected static final int FALLING = 3;
	protected static final int SHOOTING = 4;
	protected static final int PUNCHING = 5;
	
	public 
}
