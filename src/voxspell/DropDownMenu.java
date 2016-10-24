package voxspell;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import voxspell.tools.CustomFileReader;
import voxspell.tools.TextToSpeech;

/**
 * This class creates a menu bar along the top of a JFrame when instantiated
 * 
 * @author Karim Cisse
 *
 */

public class DropDownMenu {
	
	JFrame frame;
	/*
	 * Voice menu items
	 */
	private JMenuItem voice_kal;
	private JMenuItem voice_rab;
	private JMenuItem voice_nz;
	
	/*
	 * Menu items
	 */
	private JMenuItem loadList;
	private JMenuItem showStats;
	private JMenuItem showHelp;
	private JMenuItem showProgress;
	private JMenuItem showSettings;
	
	
	public DropDownMenu (JFrame frame) {
		
		// Instantiating menu bar
		JMenuBar menubar =new JMenuBar();
		frame.setJMenuBar(menubar);
		
		// Voice menu
		JMenu voiceMenu = new JMenu(makeBold("Voice"));
		voiceMenu.setToolTipText("Select a synthetic voice");
		menubar.add(voiceMenu);
		
		voice_rab = new JMenuItem(makeBold("Duke (UK)"));
		voice_kal = new JMenuItem(makeBold("Carl (US)"));
		voice_nz = new JMenuItem(makeBold("Gary (NZ)"));
		
		voiceMenu.add(voice_rab);
		voiceMenu.add(voice_kal);
		voiceMenu.add(voice_nz);
		
		createVoiceActionHandlers();
		
		// Load list menu
		JMenu loadMenu = new JMenu(makeBold("Load"));
		loadMenu.setToolTipText("Load a new list");
		menubar.add(loadMenu);
		
		loadList = new JMenuItem(makeBold("Load List"));
		loadMenu.add(loadList);

		
		createLoadListActionHandler();
		
		// Session statistics
		JMenu statsMenu = new JMenu(makeBold("Statistics"));
		menubar.add(statsMenu);
		
		showStats = new JMenuItem(makeBold("Show Stats"));
		statsMenu.add(showStats);

		
		createSessionStatistics();
		
		// Help menu
		JMenu helpMenu = new JMenu(makeBold("Help"));
		menubar.add(helpMenu);
		
		showHelp = new JMenuItem(makeBold("Show help"));
		helpMenu.add(showHelp);
		
		showHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		
		createHelpActionHandler();
		
		// Game progress menu
		JMenu progressMenu = new JMenu(makeBold("Progress"));
		menubar.add(progressMenu);
		
		showProgress = new JMenuItem(makeBold("Show progress"));
		progressMenu.add(showProgress);
		
		createProgressActionHandler();
		
		// Settings 
		JMenu settingsMenu = new JMenu(makeBold("Settings"));
		menubar.add(settingsMenu);
		
		showSettings = new JMenuItem(makeBold("Show settings"));
		settingsMenu.add(showSettings);
		
		settingsMenuActionHandler();
		


			
	}
	
	private void settingsMenuActionHandler() {
		showSettings.addActionListener( (ActionListener) -> {
			Settings.display();
		});
	}
	private void createProgressActionHandler() {
		showProgress.addActionListener( (ActionListener) -> {
			//GameProgress.getInstance();
			GameProgress.display(frame);
		});
	}
	private void createHelpActionHandler() {
		showHelp.addActionListener( (ActionListener) -> {
			Help.display(frame);
		});
		
	}
	private String makeBold(String string) {
		string = "<html><b>" + string + "</b></html>";
		return string;
	}
	/*
	 * Action handlers which change the voice
	 */
	private void createVoiceActionHandlers() {
		voice_rab.addActionListener( (ActionListener) -> {
			TextToSpeech.setVoice("(voice_rab_diphone)");
		});
		
		voice_kal.addActionListener( (ActionListener) -> {
			TextToSpeech.setVoice("(voice_kal_diphone)");
		});
		voice_nz.addActionListener( (ActionListener) -> {
			TextToSpeech.setVoice("(voice_akl_nz_jdt_diphone)");
		});
	}
	// Load menu action handlers
	private void createLoadListActionHandler() {
		
		/*
		 * This action lister allows the user to pick a new word list when the 'Load List' JMenuItem is clicked
		 */
		loadList.addActionListener((ActionListener) -> {
			
			JOptionPane.showMessageDialog(frame,
				    "Once a list is load the new words will appear in the next level attempted.\n Please read the formatting instructions in the help");
			
			final JFileChooser fc = new JFileChooser();
			int returnState = fc.showDialog(null, "Choose a word list");
			
			if(returnState == JFileChooser.APPROVE_OPTION ) {
				File file = fc.getSelectedFile();
				String filename = file.getAbsolutePath();
				CustomFileReader.changeInputWordList(filename);
			}
		});
	}
	
	/* Opens a session statistics window */
	private void createSessionStatistics() {
		showStats.addActionListener( (ActionListener) -> {
			SessionStatistics sessionStatistics = SessionStatistics.getInstance();
			
			JFrame jframe = new JFrame();
			jframe.setPreferredSize(new Dimension(400,500));
			jframe.setLocationRelativeTo(frame);
			jframe.pack();
			jframe.add(sessionStatistics);
			jframe.setVisible(true);
		});
	}
}
