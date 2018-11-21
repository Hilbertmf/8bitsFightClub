package main;

import javax.swing.JFrame;
 
public class Game {
 
	public static void main(String[] args) {
           
		JFrame window = new JFrame();
		window.setTitle("8 Bits Fight Club");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new GamePanel());
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
       
    }
       
}
