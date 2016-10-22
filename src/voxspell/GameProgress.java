package voxspell;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	private static JTextField textFieldUsername;
	private JTextField textFieldLongestStreak;

	/* This class is a singleton class */
	private static GameProgress instance;
	private JTextField currentStreakTextField;
	private int streak;
	private int longestStreak;
	
	
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
		progressBar.setBounds(12, 215, 426, 14);

		JLabel lblLevelProgress = new JLabel("Level progress");
		lblLevelProgress.setBounds(12, 188, 105, 15);
		setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 70, 105, 15);
		add(lblUsername);
		add(lblGameInformation);
		add(lblLevelProgress);
		add(progressBar);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(135, 68, 114, 25);
		add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblLongestStreak = new JLabel("LongestStreak:");
		lblLongestStreak.setBounds(12, 99, 114, 15);
		add(lblLongestStreak);

		textFieldLongestStreak = new JTextField();
		textFieldLongestStreak.setColumns(10);
		textFieldLongestStreak.setBounds(135, 97, 114, 25);
		add(textFieldLongestStreak);
		
		JLabel lblCurrentStreak = new JLabel("Current Streak:");
		lblCurrentStreak.setBounds(12, 136, 114, 15);
		add(lblCurrentStreak);
		
		currentStreakTextField = new JTextField();
		currentStreakTextField.setColumns(10);
		currentStreakTextField.setBounds(135, 134, 114, 25);
		add(currentStreakTextField);

	}

	public void adjustProgressBar(int progress) {
		progressBar.setValue(progress);
	}
	
	public static void setUsername () {
		String currentUser = UserLogIn.getInstance().getCurrentUsername();
		textFieldUsername.setText(currentUser);
	}
	
	public void setHighestStreak (boolean correct) {
		
		if (correct) {
			streak++;
		}else {
			streak = 0;
		}
		
		if(longestStreak < streak) {
			longestStreak = streak;
		}
		currentStreakTextField.setText(streak + "");
		textFieldLongestStreak.setText(longestStreak + "");
		
	}
	
	public static void display(JFrame frame) {
		
		
		JFrame jframe = new JFrame();
		jframe.getContentPane().add(GameProgress.getInstance());
		
		setUsername();
		
		jframe.setPreferredSize(new Dimension(460,300));
		jframe.setLocationRelativeTo(frame);
		jframe.pack();
		jframe.setVisible(true);
	}
}