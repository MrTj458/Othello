package model;

import java.util.ArrayList;

/**
 * Othello Game board
 * 
 * @author Trevor Hodsdon
 *
 */
public class Board {
	private String[][] board;

	public Board() {
		board = new String[8][8];

		setupBoard();
	}

	/**
	 * Set up game board with colors
	 */
	private void setupBoard() {
		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				board[r][c] = "g";
			}
		}

		// Starting tiles in the center
		board[3][3] = "w";
		board[3][4] = "b";
		board[4][3] = "b";
		board[4][4] = "w";
	}

	/**
	 * Prints the board to the console (Used for debugging)
	 */
	public void printBoard() {
		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Place a tile at a specific position
	 * 
	 * @param r
	 *            Row to place the tile at
	 * @param c
	 *            Column to place the tile at
	 * @param color
	 *            Color of the tile to be placed
	 * @return True if the color was placed
	 */
	public boolean place(int r, int c, String color) {
		if (!board[r][c].equals("y")) {
			return false;
		}

		flipColors(vDown(r, c), color);
		flipColors(vUp(r, c), color);
		flipColors(hRight(r, c), color);
		flipColors(hLeft(r, c), color);
		flipColors(dRightDown(r, c), color);
		flipColors(dRightUp(r, c), color);
		flipColors(dLeftDown(r, c), color);
		flipColors(dLeftUp(r, c), color);

		return true;
	}

	/**
	 * Flip the colors in a specific list of tiles
	 * 
	 * @param list
	 *            List of tiles to swap
	 * @param color
	 *            The color to swap to
	 */
	private void flipColors(ArrayList<Tile> list, String color) {
		// Get the opposite of the color that was passed in
		String opColor;
		if (color.equals("w")) {
			opColor = "b";
		} else {
			opColor = "w";
		}

		// Set the clicked position to the new color
		board[list.get(0).getRow()][list.get(0).getCol()] = color;

		for (int i = 1; i < list.size(); i++) {
			String curColor = list.get(i).getColor();

			// Stop if reached a blank spot
			if (curColor.equals("g") || curColor.equals("y")) {
				return;
			}

			// Swap the color
			if (curColor.equals(opColor) && containsOpColor(list, i, color)) {
				board[list.get(i).getRow()][list.get(i).getCol()] = color;
			} else {
				return;
			}

		}
	}

	/**
	 * Checks if a list of tiles still contains colors to swap
	 * 
	 * @param list
	 *            List of Tiles
	 * @param index
	 *            Index to start searching from
	 * @param color
	 *            Color to swap to
	 * @return True if there are more colors to swap
	 */
	private boolean containsOpColor(ArrayList<Tile> list, int index,
			String color) {
		for (int i = index; i < list.size(); i++) {
			String curColor = list.get(i).getColor();

			if (curColor.equals("g") || curColor.equals("y")) {
				return false;
			}

			if (curColor.equals(color)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Finds positions that a color can place at and puts them into the board
	 * 
	 * @param color
	 *            The color to check possible placement positions for
	 */
	public void setPlacements(String color) {
		// Clear all yellow tiles
		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c].equals("y")) {
					board[r][c] = "g";
				}
			}
		}

		// Check every position
		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				// Skip if there is already a tile there
				if (!board[r][c].equals("g")) {
					continue;
				}

				// Check all directions
				if (isPlaceable(vDown(r, c), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(vUp(r, c), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(hRight(r, c), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(hLeft(r, c), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(dRightDown(r, c), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(dRightUp(r, c), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(dLeftDown(r, c), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(dLeftUp(r, c), color)) {
					board[r][c] = "y";
				}

			}
		}
	}

	/**
	 * Checks if a color can be placed at a specific point on the board
	 * 
	 * @param list
	 *            List of Tiles
	 * @param color
	 *            Color to place
	 * @return True if a tile can be placed at entered position
	 */
	private boolean isPlaceable(ArrayList<Tile> list, String color) {
		boolean foundOp = false;
		boolean foundCur = false;
		String opColor;
		if (color.equals("w")) {
			opColor = "b";
		} else {
			opColor = "w";
		}

		// Can't place if there is only current player color
		if (list.size() > 1) {
			if (list.get(1).getColor().equals(color)) {
				return false;
			}
		}

		// Check the rest of the list
		for (int i = 1; i < list.size(); i++) {
			// Can't change color if there is no tile
			if (list.get(i).getColor().equals("g")
					|| list.get(i).getColor().equals("y")) {
				return false;
			}

			if (foundOp) {
				// Stop checking after finding current color
				if (foundCur) {
					return false;
				}

				// You can place a tile
				if (list.get(i).getColor().equals(color)) {
					return true;
				}
			}

			// Found opposite color from input
			if (list.get(i).getColor().equals(opColor)) {
				foundOp = true;
			}

			// Found current color
			if (list.get(i).getColor().equals(color)) {
				// Stop checking after current color
				if (foundCur == true) {
					return false;
				}

				foundCur = true;
			}
		}

		return false;
	}

	// Directions

	/**
	 * Check vertically downwards
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return List of positions
	 */
	private ArrayList<Tile> vDown(int r, int c) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (r < board[0].length) {
			positions.add(new Tile(r, c, board[r][c]));

			r++;
		}

		return positions;
	}

	/**
	 * Check vertically Upwards
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return List of positions
	 */
	private ArrayList<Tile> vUp(int r, int c) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (r >= 0) {
			positions.add(new Tile(r, c, board[r][c]));

			r--;
		}

		return positions;
	}

	/**
	 * Check horizontally to the right
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return List of positions
	 */
	private ArrayList<Tile> hRight(int r, int c) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (c < board.length) {
			positions.add(new Tile(r, c, board[r][c]));

			c++;
		}

		return positions;
	}

	/**
	 * Check horizontally to the left
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return List of positions
	 */
	private ArrayList<Tile> hLeft(int r, int c) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (c >= 0) {
			positions.add(new Tile(r, c, board[r][c]));

			c--;
		}

		return positions;
	}

	/**
	 * Check diagonally down to the right
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return List of positions
	 */
	private ArrayList<Tile> dRightDown(int r, int c) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (r != board[0].length && c != board.length) {
			positions.add(new Tile(r, c, board[r][c]));

			r++;
			c++;
		}

		return positions;
	}

	/**
	 * Check diagonally up to the right
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return List of positions
	 */
	private ArrayList<Tile> dRightUp(int r, int c) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (r != -1 && c != board.length) {
			positions.add(new Tile(r, c, board[r][c]));

			r--;
			c++;
		}

		return positions;
	}

	/**
	 * Check diagonally down to the left
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return List of positions
	 */
	private ArrayList<Tile> dLeftDown(int r, int c) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (r != board[0].length && c != -1) {
			positions.add(new Tile(r, c, board[r][c]));

			c--;
			r++;
		}

		return positions;
	}

	/**
	 * Check diagonally up to the left
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @return List of positions
	 */
	private ArrayList<Tile> dLeftUp(int r, int c) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (r != -1 && c != -1) {
			positions.add(new Tile(r, c, board[r][c]));

			r--;
			c--;
		}

		return positions;
	}

	/**
	 * @return the current board array
	 */
	public String[][] getBoard() {
		return board;
	}

	/**
	 * @return Number of white tiles on the board
	 */
	public int getWhite() {
		int total = 0;

		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c].equals("w")) {
					total++;
				}
			}
		}

		return total;
	}

	/**
	 * @return Number of black tiles on the board
	 */
	public int getBlack() {
		int total = 0;

		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c].equals("b")) {
					total++;
				}
			}
		}

		return total;
	}

	/**
	 * @return Number of yellow tiles on the board
	 */
	public int getYellow() {
		int total = 0;

		for (int r = 0; r < board[0].length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c].equals("y")) {
					total++;
				}
			}
		}

		return total;
	}
}
