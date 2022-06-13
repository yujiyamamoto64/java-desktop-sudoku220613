package sudoku.userinterface.logic;

import java.io.IOException;
import java.security.MessageDigest;

import sudoku.constants.GameState;
import sudoku.constants.Messages;
import sudoku.programdomain.IStorage;
import sudoku.programdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;

public class ControlLogic implements IUserInterfaceContract.EventListener {

	private IStorage storage;
	
	private IUserInterfaceContract.View View;
	
	public ControlLogic(IStorage storage, sudoku.userinterface.IUserInterfaceContract.View view) {
		super();
		this.storage = storage;
		View = view;
	}

	@Override
	public void onSudokuInput(int x, int y, int input) {
		try {
			SudokuGame gameData = storage.getGameData();
			int[][] newGridState = gameData.getCopyOfGridState();
			newGridState[x][y] = input;
			
			gameData = new SudokuGame (
					GameLogic.checkForCompletion(newGridState), newGridState);
			
			storage.updateGameData(gameData);
			
			View.updateSquare(x, y, input);
			
			if (gameData.getGameState() == GameState.COMPLETE) {
				View.showDialog(Messages.GAME_COMPLETE);
			}
		} catch (IOException e) {
			e.printStackTrace();
			View.showError(Messages.ERROR);
		}
		
	}

	@Override
	public void onDialogClick() {
		try {
			storage.updateGameData(GameLogic.getNewGame());
			
			View.updateBoard(storage.getGameData());
		} catch (IOException e) {
			View.showError(Messages.ERROR);
		}
		
	}

}
