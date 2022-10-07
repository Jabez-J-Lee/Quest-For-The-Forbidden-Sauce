package edu.pacific.comp55.starter;

public class Position {
	public Position[][] currentPosition;
	public Position[][] targetPosition;
	public int currentRow;
	public int currentCol;
	
	public Position(int row, int col) {
		currentRow = row;
		currentCol = col;
	}
	
	public void setRow(int row) {
		this.currentRow = row;
	}
	
	public void setCol(int col) {
		this.currentCol = col;
	}
	
	public int getRow() {
		return currentRow;
	}
	
	public int getCol() {
		return currentCol;
	}
	
	public void setPosition(int row, int col) {
		currentPosition = new Position[row][col];
	}

	public Position[][] getPosition(){
		return currentPosition;
	}

	public Position[][] getTargetPosition(Position target) {
		return target.targetPosition;
	}
}