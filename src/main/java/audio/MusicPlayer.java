package audio;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class MusicPlayer {
	
	private String musicFile;
	private AudioInputStream ais = null;
	private Clip clip;
	
	public MusicPlayer(String file) {
		
		musicFile = file;	
	}
	
	public void playSound() {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(musicFile);
		
			
			ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			
			if(ais != null) {
				clip.stop();
				clip.close();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
