package WordWin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import Comparators.StartHeightComparator;


public class SpeedTest{

	@Test
	public void speedTest() throws IOException{
	
		Dictionary dict = new Dictionary("bigDictionary.txt");
		//Dictionary dict = new Dictionary("dictionary3.txt");
		
		GameBoard gameBoard = new GameBoard("Andy.txt");
	
		int defStart = 0;
		int defSize = 5;
		
		gameBoard.analyzeEntireGameBoard();
		//ArrayList<Path> subWords = GameBoard.possibleWords;
		
		ArrayList<Path> playableWords = gameBoard.userPlayableWords(GameBoard.possibleWords);
		ArrayList<Path> winnableWords = gameBoard.winNext(GameBoard.possibleWords);
		
	//	Collections.sort(winnableWords, new StartHeightComparator());
		
//		Collections.sort(winnableWords new ReachComparator());
		
		for(Path path: winnableWords){
			Point winPoint = path.getPath().get(0);
			for(Path path2: playableWords){
				if(path2.hasPoint(winPoint.getRow(), winPoint.getCol())){
					System.out.println();
					System.out.println(path2.toString());
					System.out.println(path.toString());
				}
			}
		}
		
		
		
//		gameBoard.printWords(playableWords, defStart, defSize);
//		gameBoard.printWords(winnableWords, defStart, defSize);
		
		
//		** Make more comparators to sort winnableWords according to start point (user base)
//		** See if playableWords contains Path which contains a start point for winnableWords
//		   Two move victory if there is overlap
		
//		UI:Loading board == analyzing entire gameBoard 
//		Options to see playableWords, winnableWords, and twoMove combo that will win
		
//		For two move combo List of ArrayList??
		
	}
}