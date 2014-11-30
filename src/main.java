
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Cornell Lee
 * 
 * Generates potential playable words for user in MobileApp: WordBase
 */

public class main{
	public static void main(String args[]){
		
		try {
			Dictionary dict = new Dictionary("dictionary.txt");
			System.out.println("Dictionary successfully loaded!");
		} catch (IOException e1) {
			System.out.println("Dictionary file not present, program will not work");
		}
	
		ArrayList<GameBoard> loadedGames = new ArrayList<GameBoard>();

		System.out.println("Hello, welcome to WordWin, a cheat for WordBase, that geneartes all " +
				"possible playable words for you in WordBase. \nTo get started, load a board");
		
		String options;
		
		printMainMenu();
		options = getUserResponse();
		
		while(options.equals("load")||options.equals("view")||options.equals("find")){
			
			if(options.equals("load")){
				
				System.out.println("\nEnter the file name of the board without the extension" +
						"\nEnsure that the board is formatted properly according to the ReadMe" +
						"\nIf you modifed an existing board, please change its name");
				String fileName = getUserResponse()+".txt";
				try {
					GameBoard gameBoard = new GameBoard(fileName);
					if(!loadedGames.contains(gameBoard))
						loadedGames.add(gameBoard);
				} catch (IOException e) {
					System.out.println("Invalid file name, cannot load board");
				}
				
				printMainMenu();
				options = getUserResponse();
			}
			
			if(options.equals("view")){
				
				boolean print = true;
		
				for(GameBoard board: loadedGames)
					System.out.println(board.getIdentifer());
				
				if(loadedGames.isEmpty())
					System.out.println("You have no loaded boards!\n");
				else{
					System.out.println("Press 1 to print a gameboard");
					if(!getUserResponse().equals("1"))
						print = false;
					
					while(print){
					
						System.out.println("Enter name of board to print");
						String gameBoard = getUserResponse();
						GameBoard chosenBoard = null;
						
						for(GameBoard board: loadedGames){
							if(board.getIdentifer().equals(gameBoard))
								chosenBoard = board;
						}
						if(chosenBoard == null){
							System.out.println("This board has not been loaded");
						}
						else{
							GameBoard.printGameBoard(chosenBoard);
							System.out.println("Press 1 to print another board and anything else to cancel");
							if(!getUserResponse().equals("1"))
								print = false;
						}
					}
				}
				printMainMenu();
				options = getUserResponse();
			}
			
			if(options.equals("find")){
				System.out.println("Enter board to analyze");
				String gameBoard = getUserResponse();
				GameBoard chosenBoard = null;
				
				for(GameBoard board: loadedGames){
					if(board.getIdentifer().equals(gameBoard))
						chosenBoard = board;
				}
				
				if(chosenBoard == null){
					System.out.println("This board has not been loaded");
				}
				else{
					GameBoard.analyzeGameBoard(chosenBoard);
					ArrayList<Path> subWords = GameBoard.possibleWords;
					int defStart = 0;
					int defSize = 5;
					GameBoard.printWords(subWords, defStart, defSize);
				
					printWordAnalysisMenu();
					String cont = getUserResponse();
				
					while(cont.equals("1")||cont.equals("2")||cont.equals("3")||cont.equals("4")){
						
						if(cont.equals("1")){
							defStart = 0;
							defSize = 5;
			
							System.out.println("Enter column of position(x-value)");
							int column = Integer.parseInt(getUserResponse());
						
							System.out.println("Enter row of position(y-value)");
							int row = Integer.parseInt(getUserResponse());
				
							subWords = GameBoard.subWords(subWords,row,column);
							GameBoard.printWords(subWords, defStart, defSize);
						}
						if(cont.equals("2")){
							subWords = GameBoard.possibleWords;
							defStart = 0;
							defSize = 5;
							GameBoard.printWords(subWords, defStart, defSize);
						}
						if(cont.equals("3")){
							defStart +=5;
							defSize +=5;
							GameBoard.printWords(subWords, defStart, defSize);
						}
						if(cont.equals("4")){
							System.out.println("Enter (0-14) to print the respective shown word" +
									" and anything else to cancel");
							int toPrint = Integer.parseInt(getUserResponse());
							
							if(toPrint>=0&&toPrint<5){
								Collections.sort(subWords, new LengthComparator());
								subWords.get(defStart+toPrint).showOnBoard();
							}
							if(toPrint>4&&toPrint<10){
								Collections.sort(subWords, new ReachComparator());
								subWords.get(defStart+toPrint-5).showOnBoard();
							}
							if(toPrint>9&&toPrint<15){
								Collections.sort(subWords, new ReachComparatorMin());
								subWords.get(defStart+toPrint-10).showOnBoard();
							}
						}
						printWordAnalysisMenu();
						cont = getUserResponse();			
					}
				printMainMenu();
				options = getUserResponse();
				}
			}
		}
		System.out.println("\nGood Bye! Thanks for using! :)");
	}
	
	public static String getUserResponse(){
		Scanner scan = new Scanner(System.in);
		return scan.next();
	}
	
	public static void printMainMenu(){
		System.out.println("\nType load, to load a gameboard.\nType view, to view existing loaded gameboards" +
				"\nType find, to find possible words on an existing gameboard\nType anything else to exit");
	}
	
	public static void printWordAnalysisMenu(){
		
		System.out.println("\nPress 1 to search for a specific point \nPress 2 to show original words " +
				"\nPress 3 to show the next 5 words\nPress 4 to show a word on screen" +
				"\nPress anything else to exit");
	}
}