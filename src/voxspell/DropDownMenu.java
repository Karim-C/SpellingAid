package voxspell;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	
	/*
	 * Load menu item
	 */
	private JMenuItem loadList;
	
	/*
	 * Statistics menu item
	 */
	private JMenuItem showStats;
	
	/*
	 * Help menu item
	 */
	private JMenuItem showHelp;
	
	public DropDownMenu (JFrame frame) {
		
		// Instantiating menu bar
		JMenuBar menubar =new JMenuBar();
		frame.setJMenuBar(menubar);
		
		// Voice menu
		JMenu voiceMenu = new JMenu(makeBold("Voice"));
		menubar.add(voiceMenu);
		
		voice_rab = new JMenuItem(makeBold("rab"));
		voice_kal = new JMenuItem(makeBold("kal"));
		
		voiceMenu.add(voice_rab);
		voiceMenu.add(voice_kal);
		
		createVoiceActionHandlers();
		
		// Load list menu
		JMenu loadMenu = new JMenu(makeBold("Load"));
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
		
		createHelpActionHandler();
		

			
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
	}
	// Load menu action handlers
	private void createLoadListActionHandler() {
		
		/*
		 * This action lister allows the user to pick a new word list when the 'Load List' JMenuItem is clicked
		 */
		loadList.addActionListener((ActionListener) -> {
			final JFileChooser fc = new JFileChooser();
			int returnState = fc.showDialog(null, "Choose a word list");
			
			if(returnState == JFileChooser.APPROVE_OPTION ) {
				File file = fc.getSelectedFile();
				String filename = file.getAbsolutePath();
				CustomFileReader.changeInputWordList(filename);
			}
		});
	}
	
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
