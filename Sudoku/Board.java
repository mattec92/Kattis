/* Mattias Cederlund, mattec92@gmail.com */

public class Board {
	private int[][] numbers = new int[9][9];

	public Board (long[] in) {
		for (int row = 0; row < 9; row++) {
			for (int col = 8; col >= 0; col--) {
				numbers[row][col] = (int) in[row] % 10;
				in[row] = in[row] / 10;
			}
		}
	}

	public Board(int[][] in) {
		numbers = in;
	}

	public Board rotate() {
		int[][] rotated = new int[9][9];
		for (int fromrow = 0, tocol = 8; fromrow < 9; fromrow++, tocol--) {
			for (int fromcol = 0, torow = 0; fromcol < 9; fromcol++, torow++) {
				rotated[torow][tocol] = numbers[fromrow][fromcol];
			}
		}
		return new Board(rotated);
	}

	public Board swapCols(int first, int second) {
		Board toReturn = new Board(numbers);
		int temp;
		for (int row = 0; row < 9; row++) {
			temp = numbers[row][first];
			numbers[row][first] = numbers[row][second];
			numbers[row][second] = temp;
		}
		return toReturn;
	}

	public Board swapRows(int first, int second) {
		Board toReturn = new Board(numbers);
		int temp;
		for (int col = 0; col < 9; col++) {
			temp = numbers[first][col];
			numbers[first][col] = numbers[second][col];
			numbers[second][col] = temp;
		}
		return toReturn;
	}

	public Board swapColSections(int first, int second) {
		Board toReturn = new Board(numbers);
		int temp;
		for (int col = 0; col < 3; col++) {
			for (int row = 0; row < 9; row++) {
				temp = numbers[row][first*3 + col];
				numbers[row][first*3 + col] = numbers[row][second*3 + col];
				numbers[row][second*3 + col] = temp;
			}
		}
		return toReturn;
	}

	public Board swapRowSections(int first, int second) {
		Board toReturn = new Board(numbers);
		int temp;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				temp = numbers[first*3 + row][col];
				numbers[first*3 + row][col] = numbers[second*3 + row][col];
				numbers[second*3 + row][col] = temp;
			}
		}
		return toReturn;
	}
		
	public boolean colsegIsPermutation(Board in, int colseg) {
		for (int perm = 0; perm < 6; perm++) {
			if (colsegTest(in, colseg) == true) {
				return true;
			}
			if (perm % 2 == 0) {
				this.swapCols(colseg*3+1, colseg*3+2);
			}
			else {
				this.swapCols(colseg*3+0, colseg*3+2);
			}
		}
		return false;
	}
	
	public boolean colsegTest(Board in, int colseg) {
		int[] permutations = new int[10];
		for (int row = 0; row < 9; row++) {
			for (int col = colseg*3; col < colseg*3+3; col++) {
				if (in.numbers[row][col] != 0) {
					if (numbers[row][col] != permutations[in.numbers[row][col]] && permutations[in.numbers[row][col]] != 0) {
						return false;
					}
					else {
						permutations[in.numbers[row][col]] = numbers[row][col];
					}
				}
			}
		}
		return true;
	}
}
