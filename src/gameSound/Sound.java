package gameSound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Sound {

	Clip clip;
	boolean mute = false;
	
	public Sound(String name) {
		
		try {
			File file = new File("src/resources/audio/" + name);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void audioStart() {
//		clip.setFramePosition(0);
		clip.start();
		
	}
	
	public void audioRestart() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void audioStop() {
		clip.stop();
//		clip.close();
	}
	
	public void audioLoop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}
	
	
	
}
