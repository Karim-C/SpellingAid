package voxspell;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import voxspell.tools.BackgroundMusic;
import voxspell.tools.CustomOptionPane;
import voxspell.tools.SpecialRewardMaker;

/**
 * Voxspell main class, shows the main menu initially.
 * Contains a cardlayout to switch between:
 * the main menu/spelling quiz/view stats.
 * 
 * Mostly taken from assignment 2 code.
 * 
 * @author Will Molloy 
 */
@SuppressWarnings("serial")
public class Voxspell extends JPanel {
			
	// Cardlayout used to switch between JPanels in the cardlayout panel
	private static final CardLayout cardLayout = new CardLayout();

	// Main menu panel and the overall (Spelling Aid) panel
	private JPanel _mainMenuPanel;
	private static JPanel _cardLayoutPanel;

	private static JFrame frame;
	private SpellingQuiz _spellingQuiz;
	private HighScores _viewHighScores = new HighScores();
	
	// Other Panels next to the card layout - Statistics and Settings
	private SessionStatistics _sessionStatistics;
	private Settings _settings;
	
	// Panel to hold these other panels
	private JPanel _sidePanel;
	
	// Names for main menu buttons
	private static final String MAIN_MENU = "Return to main menu";
	private static final String NEW_QUIZ = "New Spelling Quiz";
	private static final String VIEW_HIGHSCORES = "View High Scores";
	private static final String CLEAR_STATS = "Clear Statistics";

	// Main menu buttons
	private JButton _newSpellingQuizBtn;
	private JButton _viewStatsBtn;

	private Voxspell() {  
		this.setLayout(new BorderLayout());
		
		/* Create Hidden statistic files */
		FileManager.createHiddenFiles();
		
		/* Create base panels for the program */
		createMainMenuPanel();
		_spellingQuiz = new SpellingQuiz();

		/* Adding MainMenu/Stats/SpellingQuiz to a cardlayout */
		_cardLayoutPanel = new JPanel();
		_cardLayoutPanel.setPreferredSize(new Dimension(600,700));
		_cardLayoutPanel.setLayout(cardLayout);
		_cardLayoutPanel.add(_mainMenuPanel, MAIN_MENU);
		_cardLayoutPanel.add(_spellingQuiz, NEW_QUIZ);
		_cardLayoutPanel.add(_viewHighScores, VIEW_HIGHSCORES);

		/* Adding the cardlayout and sidepanel to the overall panel */
		this.add(_cardLayoutPanel, BorderLayout.WEST);
		
		

	}

	private void createMainMenuPanel() {
		_mainMenuPanel = new JPanel();
		// Creates the boarder and the VOXSPELL logo
		_mainMenuPanel.setBorder(BorderFactory.createTitledBorder("<html><font size=\"8\"><u><i><b><font color=yellow>Vox</font><font color=white>spell</font></b></i></u></font></html>"));
		_mainMenuPanel.setBackground(new Color(0, 51, 102));
		_mainMenuPanel.setPreferredSize(new Dimension(600,700));
		_mainMenuPanel.setLayout(null);
		_newSpellingQuizBtn = new JButton(NEW_QUIZ);
		_newSpellingQuizBtn.setBounds(0, 0, 161, 25);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(220, 188, 161, 100);
		buttonPanel.setLayout(null);
		_viewStatsBtn = new JButton(VIEW_HIGHSCORES);
		_viewStatsBtn.setBounds(0, 63, 161, 25);
		buttonPanel.add(_viewStatsBtn);
		buttonPanel.add(_newSpellingQuizBtn);
		buttonPanel.setBackground(new Color(0, 51, 102));
		_mainMenuPanel.add(buttonPanel);

		createMainMenuEventHandlers();
	}

	private void createMainMenuEventHandlers() {
		_newSpellingQuizBtn.addActionListener( (ActionListener) -> {
			cardLayout.show(_cardLayoutPanel, NEW_QUIZ);
			_spellingQuiz.newQuiz();

		});

		_viewStatsBtn.addActionListener( (ActionListener) -> {
			cardLayout.show(_cardLayoutPanel, VIEW_HIGHSCORES);
			_viewHighScores.display();
		});
	}
	
	public static void showMainMenu(){
		cardLayout.show(_cardLayoutPanel, MAIN_MENU);
	}

	private static void createAndShowGUI() {
		
		/*
		 * Sets the programs look and feel
		 */
		
		try { 
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			  
			  
			} catch (Exception e) {
			    e.printStackTrace();
			}
		
		
		//System.out.println(UIManager.getLookAndFeel());
		
		Voxspell mainPanel = new Voxspell();

		frame = new JFrame("Voxspell");
		
		// stops the frame from being closed until the user confirms that the action was intended
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to exist the Voxspell","Warning", 1);
				if(dialogResult == JOptionPane.YES_OPTION){
					BackgroundMusic.getInstance().stopBackgroundMusic();
					System.exit(0);
				}
			}
		});
		
		// Creates special video
		SpecialRewardMaker spm = new SpecialRewardMaker();
		spm.execute();

		frame.getContentPane().add(mainPanel);
		frame.getContentPane().setBackground(Color.blue);
		
		frame.pack();
		frame.setLocationByPlatform(false);
		//frame.setResizable(true);
		frame.setVisible(false);
		UserLogIn uLI = new UserLogIn();
		uLI.getTheUserName(frame);
		
		/*
		 * Creates drop down menu
		 */
		new DropDownMenu(frame);
		
		/* Starts the background music*/
		BackgroundMusic.getInstance();
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
				createAndShowGUI();
			}
		});
	}
}
