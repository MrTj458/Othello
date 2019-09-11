package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.OthelloController;

/**
 * Main JPanel for the application
 * 
 * @author Trevor Hodsdon
 *
 */
public class GUIPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private OthelloController baseController;
	private JButton[][] buttons;
	private JLabel currentPlayerLbl;
	private JLabel blackNumLbl;
	private JLabel whiteNumLbl;
	
	//create separate JPanel for Game Information and Button on Grid -AL 09102019
	private JPanel gameInfo = new JPanel();
	private JPanel gamePlay = new JPanel();

	public GUIPanel(OthelloController baseController) {
		this.baseController = baseController;

		buttons = new JButton[8][8];
		currentPlayerLbl = new JLabel("", SwingConstants.CENTER);
		blackNumLbl = new JLabel("");
		whiteNumLbl = new JLabel("");

		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupPanel() {
		//create separate JPanel for Game Information -AL 09102019		
		gameInfo.setLayout(new GridLayout(1, 8, 0, 0));
		
		// Make the top menu labels
		gameInfo.add(new JLabel("Move:"));
		gameInfo.add(currentPlayerLbl);
		gameInfo.add(new JLabel(""));
		gameInfo.add(new JLabel("black:"));
		gameInfo.add(blackNumLbl);
		gameInfo.add(new JLabel("White"));
		gameInfo.add(whiteNumLbl);
		gameInfo.add(new JLabel(""));
		
		//create a separate JPanel for Buttons on the Grid -AL 09102019

		gamePlay.setLayout(new GridLayout(8, 8, 0, 0));
		
		// Add the buttons to the grid
		for (int r = 0; r < buttons[0].length; r++) {
			for (int c = 0; c < buttons.length; c++) {
				// Create button with text "row col"
				JButton curButton = new JButton(
						Integer.toString(r) + " " + Integer.toString(c));
				// Set border to fill in the grid look
				curButton.setBorder(new LineBorder(Color.black));
				// Default to green tile
				curButton.setBackground(Color.green);
				curButton.setForeground(Color.green);
				curButton.setOpaque(true);

				// Add to panel and array
				gamePlay.add(curButton);
				buttons[r][c] = curButton;
			}
		}
	}

	private void setupLayout() {
		// Make a 9x8 grid  
		//Change game layout to Border -AL 09102019
		setLayout(new BorderLayout(0, 0));
		this.add(gameInfo, BorderLayout.NORTH);
		this.add(gamePlay, BorderLayout.CENTER);
		setBackground(new Color(64, 128, 75));
		setBorder(getBorder());
	}

	private void setupListeners() {
		for (int r = 0; r < buttons[0].length; r++) {
			for (int c = 0; c < buttons.length; c++) {
				// Add listener to the button
				buttons[r][c].addActionListener(click -> {
					// Get row and column from the buttons text
					int row = Integer.parseInt(
							click.getActionCommand().split(" ")[0]);
					int col = Integer.parseInt(
							click.getActionCommand().split(" ")[1]);
					
					baseController.place(row, col);
				});
			}
		}
	}
	// replace black and white tile with image -AL 09102019
	public void setColors(String[][] board) {
		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c].equals("w")) {
//					buttons[r][c].setBackground(Color.white);
//					buttons[r][c].setForeground(Color.white);
					buttons[r][c].setBackground(new Color(64, 128, 75));
					buttons[r][c].setForeground(new Color(64, 128, 75));
					buttons[r][c].setIcon(new ImageIcon(GUIPanel.class.getResource("/view/img/wBtn.png")));
				}

				if (board[r][c].equals("y")) {
					buttons[r][c].setBackground(new Color(252, 236, 111));
					buttons[r][c].setForeground(new Color(252, 236, 111));
				}

				if (board[r][c].equals("b")) {
//					buttons[r][c].setBackground(Color.black);
//					buttons[r][c].setForeground(Color.black);
					buttons[r][c].setBackground(new Color(64, 128, 75));
					buttons[r][c].setForeground(new Color(64, 128, 75));
					buttons[r][c].setIcon(new ImageIcon(GUIPanel.class.getResource("/view/img/bBtn.png")));
				}

				if (board[r][c].equals("g")) {
					buttons[r][c].setBackground(new Color(64, 128, 75));
					buttons[r][c].setForeground(new Color(64, 128, 75));
				}
			}
		}
	}

	public void setCurrentPlayer(String player) {
		if (player.equals("b")) {
			currentPlayerLbl.setText("Black");
			currentPlayerLbl.setBackground(Color.black);
			currentPlayerLbl.setForeground(Color.white);
			currentPlayerLbl.setBorder(new LineBorder(Color.black, 1));
			currentPlayerLbl.setOpaque(true);
		} else {
			currentPlayerLbl.setText("White");
			currentPlayerLbl.setBackground(Color.white);
			currentPlayerLbl.setForeground(Color.black);
			currentPlayerLbl.setBorder(new LineBorder(Color.white, 1));
			currentPlayerLbl.setOpaque(true);
		}
	}

	public void setBlackNum(int num) {
		blackNumLbl.setText(Integer.toString(num));
	}

	public void setWhiteNum(int num) {
		whiteNumLbl.setText(Integer.toString(num));
	}
}
