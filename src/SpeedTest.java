import java.io.IOException;

import org.junit.Test;


public class SpeedTest{

	@Test
	public void speedTest() throws IOException{
	
		Dictionary dict = new Dictionary("dictionary.txt");
		//Dictionary dict = new Dictionary("dictionary3.txt");
		
		GameBoard gameBoard = new GameBoard("evil.txt");
	
		gameBoard.analyzeGameBoard();
	}
}