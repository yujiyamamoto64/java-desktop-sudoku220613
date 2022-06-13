package sudoku;

import java.io.IOException;

import javafx.stage.Stage;
import sudoku.userinterface.IUserInterfaceContract;

public class SudokuApplication {

	private IUserInterfaceContract.View uiImpl;
	
	public void start(Stage primaryStage) throws Exception {
		uiImpl = new UserInterfaceImpl(primaryStage);
		try {
			SudokuBuildLogic.build(uiImpl);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void main(String[] args) {
		

	}

}
