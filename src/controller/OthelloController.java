package controller;

import javax.swing.JOptionPane;

import model.Board;
import view.GUIFrame;
import view.GUIPanel;

/**
 * Main controller for the application
 * 
 * @author Trevor Hodsdon
 *
 */
public class OthelloController {
	private GUIFrame baseFrame;
	private GUIPanel view;
	private Board gameBoard;

	private String currentColor;

	public OthelloController() {
		baseFrame = new GUIFrame(this);
		view = baseFrame.getPanel();
		currentColor = "b";		
		gameBoard = new Board();
	}

	public void start() {
		// Initial game startup
		gameBoard.setPlacements(currentColor);
		view.setColors(gameBoard.getBoard());
		view.setCurrentPlayer(currentColor);
		view.setBlackNum(gameBoard.getBlack());
		view.setWhiteNum(gameBoard.getWhite());
	}

	public void place(int r, int c) {
		boolean placed = gameBoard.place(r, c, currentColor);

		if (placed) {
			// swap player and find new placeable tiles
			swapCurrentColor();
			gameBoard.setPlacements(currentColor);
			view.setColors(gameBoard.getBoard());
			view.setCurrentPlayer(currentColor);
			view.setBlackNum(gameBoard.getBlack());
			view.setWhiteNum(gameBoard.getWhite());

			// Check for winner
			if (gameBoard.getYellow() == 0) {
				if (gameBoard.getBlack() > gameBoard.getWhite()) {					
					view.setTitleLabel("BLACK WINS!");
//					JOptionPane.showMessageDialog(null, "Black Wins!");
				} else if (gameBoard.getBlack() < gameBoard.getWhite()) {
					view.setTitleLabel("WHITE WINS!");
//					JOptionPane.showMessageDialog(null, "White Wins!");
				} else {
					view.setTitleLabel("TIE GAME!");
					JOptionPane.showMessageDialog(null, "Tie Game!");
				}
			}
		}
	}

	// Swap the current player
	private void swapCurrentColor() {
		if (currentColor.equals("w")) {
			currentColor = "b";
		} else {
			currentColor = "w";
		}
	}
}
