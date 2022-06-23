package sudoku.buildlogic;

import java.io.IOException;

import sudoku.computationlogic.GameLogic;
import sudoku.programdomain.IStorage;
import sudoku.programdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;
import sudoku.userinterface.logic.ControlLogic;

public class SudokuBuildLogic {

	public static void Build(IUserInterfaceContract.View userInterface) throws IOException {
		SudokuGame initialState;
		IStorage storage = new LocalStorageImpl();
		
		try {
			initialState = storage.getGameData();
		} catch (IOException e) {
			initialState = GameLogic.getNewGame();
			storage.updateGameData(initialState);
		}
		
		IUserInterfaceContract.EventListener uiLogic = 
				new ControlLogic(storage, userInterface);
		
		userInterface.setListener(uiLogic);
		userInterface.updateBoard(initialState);
	}
}
