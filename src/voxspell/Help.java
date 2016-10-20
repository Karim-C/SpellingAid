package voxspell;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class Help extends JPanel {

	/**
	 * Create the panel.
	 */
	public Help() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JTextPane txtpntipsAndTricks = new JTextPane();
		txtpntipsAndTricks.setContentType("text/html");
		txtpntipsAndTricks.setText("<html>\n<h1>Tips and tricks</h1>\n<p>Help on how to use the different features of the tool. In the future this section will have information on how the scoring system works and how to use the application in general</p>\n</html>");
		add(txtpntipsAndTricks);
		txtpntipsAndTricks.setEditable(false);

	}
	
	public static void display(JFrame frame) {
		JFrame jframe = new JFrame();
		jframe.add(new Help());
		jframe.setPreferredSize(new Dimension(400,500));
		jframe.setLocationRelativeTo(frame);
		jframe.pack();
		jframe.setVisible(true);
	}

}
