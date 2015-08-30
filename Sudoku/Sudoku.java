/* Mattias Cederlund, mattec92@gmail.com */

import java.util.*;

public class Sudoku {
	ArrayList<Board> oldBoards;
	ArrayList<Board> newBoards;

	public Sudoku() {
		oldBoards = new ArrayList<Board>();
		newBoards = new ArrayList<Board>();
	}

	public void getBoards() {
		Scanner sc = new Scanner(System.in);
		int boardcount = sc.nextInt();
		long[] inputBoard = new long[9];
		for (int i = 0; i < boardcount; i++) {
			for (int k = 0; k < 9; k++) {
				inputBoard[k] = sc.nextLong();
			}
			oldBoards.add(new Board(inputBoard));
			for (int k = 0; k < 9; k++) {
				inputBoard[k] = sc.nextLong();
			}
			newBoards.add(new Board(inputBoard));
			sc.nextLine();
		}
		sc.close();
	}

	public void solve() {
		for (int i = 0; i < oldBoards.size(); i++) {
			if (solve(oldBoards.get(i), newBoards.get(i)) == true) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
	}

	private boolean solve(Board oldboard, Board newboard) {
		for (int rot = 0; rot < 2; rot++) {
			for (int rowseg = 0; rowseg < 6; rowseg++) {
				for (int row0 = 0; row0 < 6; row0++) {
					for (int row1 = 0; row1 < 6; row1++) {
						for (int row2 = 0; row2 < 6; row2++) {
							for (int colseg = 0; colseg < 6; colseg++) {
								if (oldboard.colsegIsPermutation(newboard, 0)) {
									if (oldboard.colsegIsPermutation(newboard, 1)) {
										if (oldboard.colsegIsPermutation(newboard, 2)) {
											return true;
										}
									}
								}
								if (colseg % 2 == 0) {
									oldboard = oldboard.swapColSections(1, 2);
								}
								else {
									oldboard = oldboard.swapColSections(0, 2);
								}
							}
							if (row2 % 2 == 0) {
								oldboard = oldboard.swapRows(7, 8); //+6
							}
							else {
								oldboard = oldboard.swapRows(6, 8); //+6
							}
						}
						if (row1 % 2 == 0) {
							oldboard = oldboard.swapRows(4, 5); //+3
						}
						else {
							oldboard = oldboard.swapRows(3, 5); //+3
						}
					}
					if (row0 % 2 == 0) {
						oldboard = oldboard.swapRows(1, 2);
					}
					else {
						oldboard = oldboard.swapRows(0, 2);
					}
				}
				if (rowseg % 2 == 0) {
					oldboard = oldboard.swapRowSections(1, 2);
				}
				else {
					oldboard = oldboard.swapRowSections(0, 2);
				}
			}
			oldboard = oldboard.rotate();
		}
		return false;
	}
}