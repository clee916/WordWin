package WordWin;

import java.io.IOException;
import org.junit.Test;

public class SpeedTest{

	@Test
	public void analyzeEntreGameBoardSpeedTest() throws IOException{
		new Dictionary("bigDictionary.txt");
		GameBoard gameBoard = new GameBoard("board.txt");
		gameBoard.analyzeEntireGameBoard();

	}
	
	@Test
	public void analyzeGameBoardSpeedTest() throws IOException{
		new Dictionary("bigDictionary.txt");
		GameBoard gameBoard = new GameBoard("board.txt");
		gameBoard.analyzeGameBoard();

	}
}