package voxspell;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class creates a menu bar along the top of a JFrame when instantiated
 * 
 * @author Karim Cisse
 *
 */

public class DropDownMenu {
	public DropDownMenu (JFrame frame) {
		JMenuBar menubar =new JMenuBar();
		frame.setJMenuBar(menubar);
		JMenu file = new JMenu("hello");
		JMenuItem f = new JMenuItem("Exit");
		file.add(f);
		menubar.add(file);
	}
}
