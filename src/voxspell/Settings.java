package voxspell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import voxspell.tools.BackgroundMusic;
import javax.swing.JSlider;

/**
 * 
 * 
 * @author Karim Cisse
 */
@SuppressWarnings("serial")
public class Settings extends JPanel {
	private JLabel lblBackgorundMusic;
	private JSlider slider;
	private JLabel label;
	
	public Settings() {
		this.setPreferredSize(new Dimension(500,300));
		setBackground(new Color(0, 51, 102));
		setLayout(null);
		
		JLabel lblSettings = new JLabel("<html><h1>Settings</h1></html>");
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(new Color(255, 255, 255));
		lblSettings.setBounds(12, 12, 426, 37);
		add(lblSettings);
		
		lblBackgorundMusic = new JLabel("Backgorund Music Volume");
		lblBackgorundMusic.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackgorundMusic.setForeground(new Color(255, 255, 255));
		lblBackgorundMusic.setBounds(12, 106, 426, 25);
		add(lblBackgorundMusic);
		
		slider = new JSlider();
		slider.setBounds(12, 185, 476, 31);
		add(slider);
		
		label = new JLabel(makeBoldBig(slider.getValue() + "%"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(12, 143, 476, 25);
		add(label);
		
		addActionListeners();
		
	}

	private void addActionListeners() {
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				BackgroundMusic.getInstance().increaseBackgroundMusic(slider.getValue());
				label.setText(makeBoldBig(slider.getValue() + "%"));
				
			}
			
		});
	}
	
	private String makeBoldBig(String str) {
		return "<html><h1><font color=white>" + str + "</font></h1></html>";
	}
	
	public static void display() {
		JFrame jframe = new JFrame();
		jframe.getContentPane().add(new Settings());
		jframe.setPreferredSize(new Dimension(500,300));
		jframe.pack();
		jframe.setVisible(true);
	}
}
