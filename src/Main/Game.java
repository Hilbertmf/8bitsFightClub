package Main;

import javax.swing.JFrame;
 
public class Game {
 
	public static void main(String[] args) {
           
		JFrame window = new JFrame();
		window.setTitle("Joguinho");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new GamePanel());
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
       
    }
       
}
