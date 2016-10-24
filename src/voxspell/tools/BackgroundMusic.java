package voxspell.tools;


import java.awt.Frame;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * The BackgroundMusic class contains the methods associated with playing background music and controlling it.
 * 
 * @author Karim Cisse
 *
 */
public class BackgroundMusic {
	
	Clip clip = null;
	FloatControl gainControl;
	private float volume;
	
	/* This class is a singleton class */
	private static BackgroundMusic instance;
	
	
	public static BackgroundMusic getInstance() {
		if (instance == null) {
			instance = new BackgroundMusic();
		}
		return instance;
	}
	
	private BackgroundMusic () {
		musicPlayerInit();
	}

	/* This is where the music file is read in and played */
	private void musicPlayerInit() {
		AudioInputStream audioStream = null;
		
		/* Here the file is read into the input stream */
		try {
			audioStream = AudioSystem.getAudioInputStream(new File("ChillWave.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			musicError();
		}
		
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	}
	
	public void stopBackgroundMusic() {
		clip.stop();
		
	}
	
	public void playBackgroundMusic () {
		if (instance != null) {
			clip.start();
		}
	}
	
	public void decreaseBackgroundMusic() {
		volume --;
		gainControl.setValue(volume);
	}
	public void increaseBackgroundMusic(int i) {
		volume = (float) (-100 + i);
		
		// volume can't be set below -70.0f 
		volume = (volume < -70.0f) ? -70.0f : volume;
		
		System.out.println(volume);
		//volume = (volume >= 0) ? volume++ : 0;
		gainControl.setValue(volume);
	}
	
	/* If an error occurs while trying to load the music file or play the file this message is shown*/
	private void musicError() {
		JOptionPane.showMessageDialog(new Frame(),
		    "There was a problem with the background music. Sorry!",
		    "Background music",
		    JOptionPane.INFORMATION_MESSAGE);
	}
}
