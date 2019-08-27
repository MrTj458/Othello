package model;

import java.util.ArrayList;

public class ArrayIterator {
	/**
	 * Check vertically downwards
	 * 
	 * @param r
	 *            row
	 * @param c
	 *            column
	 * @param board
	 *            The current game board
	 * @return List of positions
	 */
	public ArrayList<Tile> vDown(int r, int c, String[][] board) {
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
	 * @param board
	 *            The current game board
	 * @return List of positions
	 */
	public ArrayList<Tile> vUp(int r, int c, String[][] board) {
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
	 * @param board
	 *            The current game board
	 * @return List of positions
	 */
	public ArrayList<Tile> hRight(int r, int c, String[][] board) {
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
	 * @param board
	 *            The current game board
	 * @return List of positions
	 */
	public ArrayList<Tile> hLeft(int r, int c, String[][] board) {
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
	 * @param board
	 *            The current game board
	 * @return List of positions
	 */
	public ArrayList<Tile> dRightDown(int r, int c, String[][] board) {
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
	 * @param board
	 *            The current game board
	 * @return List of positions
	 */
	public ArrayList<Tile> dRightUp(int r, int c, String[][] board) {
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
	 * @param board
	 *            The current game board
	 * @return List of positions
	 */
	public ArrayList<Tile> dLeftDown(int r, int c, String[][] board) {
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
	 * @param board
	 *            The current game board
	 * @return List of positions
	 */
	public ArrayList<Tile> dLeftUp(int r, int c, String[][] board) {
		ArrayList<Tile> positions = new ArrayList<Tile>();

		while (r != -1 && c != -1) {
			positions.add(new Tile(r, c, board[r][c]));

			r--;
			c--;
		}

		return positions;
	}
}
