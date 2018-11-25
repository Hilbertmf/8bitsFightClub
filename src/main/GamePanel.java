package main;

import javax.swing.JPanel;

import gameState.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	// dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;

	private Thread thread;

	private BufferedImage image;
	private Graphics2D graphics;
	private boolean isRunning;

	private GameStateManager gsm;

	// constructor time
	public GamePanel() {
		
		super();
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	
	public void addNotify() { 
		super.addNotify();
		if(thread == null) {
			addKeyListener(this); 
			thread = new Thread(this); 
			thread.start();
		}
	}
	 
	// initializes variables
	private void init() {

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) image.getGraphics();

		isRunning = true;

		gsm = new GameStateManager();
	}

	// the "main" function
	public void run() {

		init();

		int FPS = 45;
		int targetTime = 1000 / FPS;

		long start;
		long elapsed;
		long wait;

		// simple game loop
		while (isRunning) {

			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = (System.nanoTime() - start) / 1000000;

			wait = targetTime - elapsed;
			if (wait < 0)
				wait = 5;

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void update() {
		gsm.update();
	}

	// draws the game onto an off-screen buffered image
	private void draw() {
		gsm.draw(graphics);
		
	}

	// draws the off-screen buffered image to the screen
	private void drawToScreen() {
		Graphics graphics2 = getGraphics();
		graphics2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		graphics2.dispose();
	}

	public void keyTyped(KeyEvent k) {

	}

	public void keyPressed(KeyEvent k) {
		gsm.keyPressed(k.getKeyCode());
	}

	public void keyReleased(KeyEvent k) {
		gsm.keyReleased(k.getKeyCode());
	}

}
