package voxspell;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import voxspell.tools.BackgroundMusic;

/**
 * This class contains methods associated with displaying the users progress
 * through the the quiz in a game like fashion
 * 
 * @author Karim Cisse
 *
 */

@SuppressWarnings("serial")
public class GameProgress extends JPanel {
	private JProgressBar progressBar;
	private JTextField textFieldUsername;
	private JTextField textFieldLongestStreak;

	/* This class is a singleton class */
	private static GameProgress instance;
	
	
	public static GameProgress getInstance() {
		if (instance == null) {
			instance = new GameProgress();
		}
		return instance;
	}
	
	/**
	 * Create the panel.
	 */
	public GameProgress() {

		JLabel lblGameInformation = new JLabel("<html><h1>Game Information</h1></html>");
		lblGameInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameInformation.setBounds(12, 5, 426, 47);

		progressBar = new JProgressBar();
		progressBar.setBounds(12, 274, 426, 14);

		JLabel lblLevelProgress = new JLabel("Level progress");
		lblLevelProgress.setBounds(12, 247, 105, 15);
		setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 129, 105, 15);
		add(lblUsername);
		add(lblGameInformation);
		add(lblLevelProgress);
		add(progressBar);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(135, 127, 114, 19);
		add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblLongestStreak = new JLabel("LongestStreak:");
		lblLongestStreak.setBounds(12, 181, 114, 15);
		add(lblLongestStreak);

		textFieldLongestStreak = new JTextField();
		textFieldLongestStreak.setColumns(10);
		textFieldLongestStreak.setBounds(135, 179, 114, 19);
		add(textFieldLongestStreak);

	}

	public void adjustProgressBar(int progress) {
		progressBar.setValue(progress);
	}
	
	public void setUsername (String username) {
		
	}
	
	public void setHighestStreak (int streak) {
		
	}
	
	public static void display(JFrame frame) {
		JFrame jframe = new JFrame();
		jframe.add(GameProgress.getInstance());
		jframe.setPreferredSize(new Dimension(460,500));
		jframe.setLocationRelativeTo(frame);
		jframe.pack();
		jframe.setVisible(true);
	}
}
