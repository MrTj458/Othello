package model;

/**
 * An individual tile on the Othello game board
 * 
 * @author Trevor Hodsdon
 *
 */
public class Tile {
	private int row;
	private int col;
	private String color;

	public Tile(int row, int col, String color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String getColor() {
		return color;
	}
}
