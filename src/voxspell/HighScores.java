package voxspell;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

/**
 * This class contains the methods that handle everything associated with displaying the longest streaks of each user
 * 
 * @author Karim Cisse
 *
 */
@SuppressWarnings("serial")
public class HighScores extends JPanel {

	private static HighScores instance;
	private JTable HighScorestable;
	
	// this class is a singleton
	public static HighScores getInstance() {
		if (instance == null) {
			instance = new HighScores();
		}
		return instance;
	}
	
    public HighScores(){
    	
    	updateTable();

        HighScorestable.setPreferredScrollableViewportSize(new Dimension(450,63));
        HighScorestable.setFillsViewportHeight(true);

        JScrollPane highScoreScrollPane=new JScrollPane(HighScorestable);
        highScoreScrollPane.setBounds(12, 68, 426, 177);
        highScoreScrollPane.setVisible(true);
        setLayout(null);
        add(highScoreScrollPane);
        
        JLabel lblLongestStreaks = new JLabel("<html><h1>Longest Streaks</h1></hmtl>");
        lblLongestStreaks.setHorizontalAlignment(SwingConstants.CENTER);
        lblLongestStreaks.setBounds(12, 12, 426, 32);
        add(lblLongestStreaks);
        
        JButton btnReturnToMain = new JButton("Return to Main Menu");
        btnReturnToMain.setBounds(57, 263, 207, 25);
        add(btnReturnToMain);
        
        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(304, 263, 117, 25);
        add(btnReset);

    }
    
    public void updateTable() {
    	 String [] header={"Username","Longest Streak"};
    	 String [][] data={{"usr", "2344"}};
    	 DefaultTableModel model = new DefaultTableModel(data,header);
    	 HighScorestable = new JTable(model);

    }
    
    public String [][] getLongestSteaks() {
    	return null;
    }

    public static void main(String [] a) {

        JFrame jf=new JFrame();
        HighScores tab= new HighScores();
        jf.setTitle("Table");
        jf.setSize(500, 500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().add(tab);




    }
}
