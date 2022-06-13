package sudoku.programdomain;

import java.io.IOException;

public interface IStorage {
	void updateGameData(Sudoku game) throws IOException;
	SudokuGame getGameData() throws IOException;
}
