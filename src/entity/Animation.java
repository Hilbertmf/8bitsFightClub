package entity;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames;
	private int currentFrame;
	// timer between frames
	private long startTimer;
	// how long between each frame
	private long delay;
	
	private boolean wasPlayedOnce;
	
	public Animation() {
		wasPlayedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTimer = System.nanoTime();
		wasPlayedOnce = false;
	}
	
	public void setDelay(long delay) { this.delay = delay; }
	public void setFrame(int currentFrame) {this.currentFrame = currentFrame; }
	
	public void update() {
		// handles the logic for determining whether or not move to the next frame
		if(delay == -1) return;
		
		long elapsed = (System.nanoTime() - startTimer) / 1000000;
		if(elapsed > delay) {
			currentFrame++;
			startTimer = System.nanoTime();
		}
		if(currentFrame == frames.length) {
			currentFrame = 0;
			wasPlayedOnce = true;
		}
	}
	
	// getters
	public int getFrame() { return currentFrame; }
	public BufferedImage getImage() { return frames[currentFrame]; }
	public boolean hasPlayedOnce() { return wasPlayedOnce; }
	
}
