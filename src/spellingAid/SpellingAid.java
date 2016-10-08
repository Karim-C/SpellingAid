package spellingAid;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class SpellingAid extends JFrame {

	private JPanel _contentPane;
	private static final CardLayout cardLayout = new CardLayout();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpellingAid frame = new SpellingAid();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpellingAid() {
		
		/*
		 * Could display message asking user whether they want to save the session
		 */
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to exist voxspell","Exist", JOptionPane.YES_NO_CANCEL_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					System.exit(0);
				}
				
			}
		});
		
		setBounds(100, 100, 450, 300);
		_contentPane = new JPanel();
		_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_contentPane.setLayout(cardLayout);
		setContentPane(_contentPane);
	}

}
