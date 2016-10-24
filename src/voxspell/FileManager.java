package voxspell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class to manage the hidden files used for storing statistics.
 * 
 * @author Will Molloy
 *
 */
public class FileManager {

	// Files -- can be accessed by classes in the same package
	
	public static final File HIGH_STREAKS = new File(".high_streaks");
	
	private static final File[] _hiddenFiles = { HIGH_STREAKS };
	
	
	/**
	 * Creates the hidden files for stats and failed words used in review mistakes
	 * 
	 * WILL NOT overwrite them if they already exist.
	 */
	public static void createHiddenFiles(){
		try {
			for (File f : _hiddenFiles){
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clears the hidden files for stats and failed words by writing an empty string to them.
	 * 
	 * DOES NOT delete the files.
	 */
	public static void clearStatisticFiles() {
		try {
			for (File f : _hiddenFiles){
				PrintWriter pw = new PrintWriter(f);
				pw.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
