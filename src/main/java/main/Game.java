package main;

/*
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
*/

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
