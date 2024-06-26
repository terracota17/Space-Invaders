package tp1.logic;

public class Position {

	private int col;
	private int row;
	
	public Position(int col, int row) {
		
		this.col = col;
		this.row = row;
				
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	
	public boolean isInFinalRowAnyOne(int dimY) {
		return this.row == dimY -1;
	}
	
	public Position createPosShockWave( ) {
		return new Position(this.col, this.row);
	}
	
	
	public boolean isInExplosionRange(Position posExplosive) {
		int colDiff = Math.abs(this.col - posExplosive.col);
	    int rowDiff = Math.abs(this.row - posExplosive.row);

	    return colDiff <= 1 && rowDiff <= 1;
	}
	
	public boolean checkAttack(Position pos) {
		return (this.col == pos.col && this.row == pos.row);
	}
	public boolean checkIfOutsideMap(int dimX, int dimY) {
		return !isWithinBounds(this.col, this.row, dimX, dimY);
	}
	
	public void moveUp() {
		
	}
	
	public Position createPosSuperLaser()  {
		return new Position(col, row);
	}
	
	public Position deployBomb()  {
		return new Position(this.col, this.row+1);
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public Position createNewPosForBomb() {
		return new Position(this.col, this.row);
	}
	
	//diagonales cualquieras y antidiagonales dentro de las dimensiones del TABLERO
	public boolean isDiagonalOrAntidiagonalTo(Position pos, int dimensionX, int dimensionY) {
	    int colDiff = Math.abs(this.col - pos.col);
	    int rowDiff = Math.abs(this.row - pos.row);

	    // Verifica si la posición es diagonal o antidiagonal
	    boolean isDiagonal = colDiff == rowDiff && isWithinBounds(pos.getCol(), pos.row, dimensionX, dimensionY);
	    boolean isAntidiagonal = colDiff == rowDiff && (this.col + this.row) == (pos.col + pos.row)
	            && isWithinBounds(pos.col, pos.row, dimensionX, dimensionY);

	    return isDiagonal || isAntidiagonal;
	}
	
	//diagonal 
	public boolean isDiagonalTo(Position pos) {
	    int colDiff = Math.abs(this.col - pos.col);
	    int rowDiff = Math.abs(this.row - pos.row);

	    // Verifica si la diferencia en las columnas y las filas es 1, lo que indica una posición diagonal
	    return colDiff == 1 && rowDiff == 1;
	}
		
	public boolean isAnyRightBorder(int dimx) {
		return this.col == dimx-1;
	}
	public boolean isAnyLeftBorder(int dimy) {
		return this.col - 1 < 0;
	}
	
	public Position shoot()  {
		return (new Position(col, this.row  ));
	}
	
	public void movePosition(Move move) {
		this.col += move.getX();
		this.row += move.getY();
	}
	
	public boolean isInBorder(int dimX, Move dir) {
		return ((this.col == 0  && dir == (Move.LEFT)) 
				||  (dir == (Move.RIGHT) && this.col == dimX-1));
	}

	public boolean canPlayerMove(Move move, int dimensionX, int dimensionY) {
	    return (isWithinBounds(this.col + move.getX(), this.row + move.getY(), dimensionX, dimensionY));
	}

	private boolean isWithinBounds(int col, int row, int dimensionX, int dimensionY) {
	    return col >= 0 && col < dimensionX && row >= 0 && row < dimensionY;
	}

	public boolean landed(int dimensionY) {
		return this.row == dimensionY;
	}
	
	public boolean isOnPosition(Position pos) {

		return pos != null && (this.col == pos.col && pos.row == row);
	}
	
	public boolean equals(Position pos) {	
		return (pos != null) && (this.col == pos.col && (this.row == pos.row)) ? true : false;
	}

	@Override
	public String toString() {
		return "(" + this.col + ", "+ this.row + ")";
	}
	
	
}
