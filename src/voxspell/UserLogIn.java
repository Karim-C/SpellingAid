package voxspell;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import voxspell.tools.BackgroundMusic;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

/**
 * This class is responsible for creating the log in pop-up getting the users user-name
 * 
 * @author Karim Cisse
 *
 */

@SuppressWarnings("serial")
public class UserLogIn extends JPanel{
	private JTextField usernameTextField;
	private JButton btnSubmit;
	private JButton btnExit;
	private JFrame mainFrame;
	private JFrame jframe;
	protected static String currentUsername;

	
    // This class is a singleton class 
	private static UserLogIn instance;
	
	
	public static UserLogIn getInstance() {
		if (instance == null) {
			instance = new UserLogIn();
		}
		return instance;
	}
	
	public UserLogIn () {
		
		
		setBackground(new Color(0, 51, 153));
		setLayout(null);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(115, 147, 243, 25);
		add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(27, 147, 106, 15);
		add(lblUsername);
		
		JLabel lblLoginHeader = new JLabel("<html><h1>Log In</h1></html>");
		lblLoginHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginHeader.setForeground(Color.WHITE);
		lblLoginHeader.setBounds(12, 28, 426, 29);
		add(lblLoginHeader);
		
		btnSubmit = new JButton("Submit");

		btnSubmit.setBounds(115, 197, 117, 25);
		add(btnSubmit);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(241, 197, 117, 25);
		add(btnExit);
		
		addedActionListers();
	}
	
	private void addedActionListers() {
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUsername = usernameTextField.getText();
				mainFrame.setVisible(true);
				jframe.setVisible(false);
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}
	private void display() {
		jframe = new JFrame();
		jframe.getContentPane().add(this);
		jframe.setPreferredSize(new Dimension(500,400));
		jframe.pack();
		jframe.setVisible(true);
		
		jframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				BackgroundMusic.getInstance().stopBackgroundMusic();
				System.exit(0);
			}
		});
	}
	
	/* Displays a window when the application is started asking for the users user-name */
	public void getTheUserName(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		display();
		
	}
	
	/* Returns the recorded user-name */
	public String getCurrentUsername() {
		String user = currentUsername;
		return currentUsername;
	}
}
