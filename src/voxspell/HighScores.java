package voxspell;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import voxspell.tools.BackgroundMusic;
import voxspell.tools.CaseInsensitiveComparator;
import voxspell.tools.CustomFileReader;
import voxspell.tools.CustomOptionPane;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private ArrayList<String[]> highStreaks;
	private JTable _streakTable;
	private JScrollPane highScoreScrollPane;
	
	// this class is a singleton
	public static HighScores getInstance() {
		if (instance == null) {
			instance = new HighScores();
		}
		return instance;
	}
	
    public HighScores(){
    	setBackground(new Color(0, 51, 102));
    	

        highScoreScrollPane=new JScrollPane(HighScorestable);
        highScoreScrollPane.setBounds(87, 75, 426, 302);
        highScoreScrollPane.setVisible(true);
        setLayout(null);
        //add(highScoreScrollPane);
        
        JLabel lblLongestStreaks = new JLabel("<html><h1>Longest Streaks</h1></hmtl>");
        lblLongestStreaks.setForeground(new Color(255, 255, 255));
        lblLongestStreaks.setHorizontalAlignment(SwingConstants.CENTER);
        lblLongestStreaks.setBounds(87, 12, 426, 32);
        add(lblLongestStreaks);
        
        JButton btnReturnToMain = new ReturnToMainMenuBtn(this);
        btnReturnToMain.setBounds(87, 389, 218, 25);
        add(btnReturnToMain);
        
        JButton btnReset =new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    				int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to permently delete all high scores","Warning", 1);
    				if(dialogResult == JOptionPane.YES_OPTION){
                		CustomFileReader fileReader = new CustomFileReader();
            			fileReader.clearStreaks(FileManager.HIGH_STREAKS);
    				}
        	}
        });
        btnReset.setBounds(396, 389, 117, 25);
        add(btnReset);
        
        highScoreScrollPane.setPreferredSize(new Dimension(500, 500));
		this.add(highScoreScrollPane, BorderLayout.NORTH);
        

    }
    
    public void updateTable() {
    	readStatisticFiles();
    	generateAndShowTable();
    	showTable();

    }
    
    public String [][] getLongestSteaks() {
    	return null;
    }
    
	private void readStatisticFiles() {
		highStreaks = new ArrayList<String[]>();
		CustomFileReader fileReader = new CustomFileReader();
			fileReader.readFileByLine(FileManager.HIGH_STREAKS, highStreaks);
		
			Collections.sort(highStreaks, new Comparator < String[] > () {
			    public int compare(String[] s1, String[] s2) {
			        if (Integer.parseInt(s1[1]) > Integer.parseInt(s2[1])){
			        	return -1;
			        }
			        if (Integer.parseInt(s1[1]) < Integer.parseInt(s2[1])){
			        	return 1;
			        }
			        return 0;
			    }
			});
	}
	
	/**
	 * Generates the statistics table based on what is in the sorted list 'sortedWordsToDisplay'
	 * and is in the hidden statistic files.
	 * 
	 * Found code here: http://stackoverflow.com/a/11095952/6122976
	 */
	private void generateAndShowTable() {
		String[] columnNames = { "Username" , "Longest Streak" };

		// Create the JTable using the List of stats and String[] of columnNames
		TableModel tableModel = new DefaultTableModel(highStreaks.toArray(new Object[][] {}), columnNames){
			@Override	// Makes all of the cells in the table uneditable
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		_streakTable = new JTable(tableModel);		
		showTable();

		// Let the user know if there are no stats -- down here so that it shows the table as empty
		if (highStreaks.size() == 0){
			JOptionPane.showMessageDialog(this, "There are no high scores yet!",
					"No High Scores", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * Shows the statistic table
	 */
	private void showTable() {
		_streakTable.setPreferredScrollableViewportSize(new Dimension(350,500));
		_streakTable.setFillsViewportHeight(true);
		highScoreScrollPane.setViewportView(_streakTable);
		this.repaint(); // repaints all components so that the scroll pane adjusts to the new table size
	}
	
	//public added

    public void display() {
    	updateTable();
    	

    }
}
