package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		// Make the top menu labels
		this.add(new JLabel("Move:"));
		this.add(currentPlayerLbl);
		this.add(new JLabel(""));
		this.add(new JLabel("black:"));
		this.add(blackNumLbl);
		this.add(new JLabel("White"));
		this.add(whiteNumLbl);
		this.add(new JLabel(""));

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
				this.add(curButton);
				buttons[r][c] = curButton;
			}
		}
	}

	private void setupLayout() {
		// Make a 9x8 grid
		setLayout(new GridLayout(9, 8, 6, 6));
		setBackground(new Color(64, 128, 75));
	}

	private void setupListeners() {
		for (int r = 0; r < buttons[0].length; r++) {
			for (int c = 0; c < buttons.length; c++) {
				buttons[r][c].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						// Get row and column from the buttons text
						int row = Integer.parseInt(
								click.getActionCommand().split(" ")[0]);
						int col = Integer.parseInt(
								click.getActionCommand().split(" ")[1]);

						baseController.place(row, col);
					}
				});
			}
		}
	}

	public void setColors(String[][] board) {
		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c].equals("w")) {
					buttons[r][c].setBackground(Color.white);
					buttons[r][c].setForeground(Color.white);
				}

				if (board[r][c].equals("y")) {
					buttons[r][c].setBackground(new Color(252, 236, 111));
					buttons[r][c].setForeground(new Color(252, 236, 111));
				}

				if (board[r][c].equals("b")) {
					buttons[r][c].setBackground(Color.black);
					buttons[r][c].setForeground(Color.black);
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
