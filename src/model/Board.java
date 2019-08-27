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
	private ArrayIterator iterator;

	public Board() {
		board = new String[8][8];
		iterator = new ArrayIterator();

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

		flipColors(iterator.vDown(r, c, board), color);
		flipColors(iterator.vUp(r, c, board), color);
		flipColors(iterator.hRight(r, c, board), color);
		flipColors(iterator.hLeft(r, c, board), color);
		flipColors(iterator.dRightDown(r, c, board), color);
		flipColors(iterator.dRightUp(r, c, board), color);
		flipColors(iterator.dLeftDown(r, c, board), color);
		flipColors(iterator.dLeftUp(r, c, board), color);

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
		String opColor;
		if (color.equals("w")) {
			opColor = "b";
		} else {
			opColor = "w";
		}
		
		// Swap clicked tile
		board[list.get(0).getRow()][list.get(0).getCol()] = color;
		
		ArrayList<Tile> swapList = new ArrayList<Tile>();
		// Loop through inputed list
		for (int i = 1; i < list.size(); i++) {
			// If the tile is the opposite color add it to the list to get swapped
			if (list.get(i).getColor().equals(opColor)) {
				swapList.add(list.get(i));
			}
			
			// If the list hits a g or y tile before the inputed color again, return without swapping colors
			if (list.get(i).getColor().equals("g") || list.get(i).getColor().equals("y")) {
				return;
			}
			
			// If the list runs into the inputed color swap all of the tiles in the swapList
			if (list.get(i).getColor().equals(color)) {
				for(int j = 0; j < swapList.size(); j++) {
					board[swapList.get(j).getRow()][swapList.get(j).getCol()] = color;
				}
				// Swapped all colors return to break out of method
				return;
			}
		}
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
				if (isPlaceable(iterator.vDown(r, c, board), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(iterator.vUp(r, c, board), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(iterator.hRight(r, c, board), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(iterator.hLeft(r, c, board), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(iterator.dRightDown(r, c, board), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(iterator.dRightUp(r, c, board), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(iterator.dLeftDown(r, c, board), color)) {
					board[r][c] = "y";
				}

				if (isPlaceable(iterator.dLeftUp(r, c, board), color)) {
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
