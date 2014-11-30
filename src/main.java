
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		} catch (IOException e1) {
			System.out.println("Dictionary file not present");
		}
		
		GameBoard gameBoard = null;
		System.out.println("Enter the file name where the board is stored without extension");
		String fileName = getUserResponse()+".txt";
		try {
			gameBoard = new GameBoard(fileName);
		} catch (IOException e) {
			System.out.println("Invalid file name, cannot load board");
		}
		
		GameBoard.analyzeGameBoard();
		
		List<Path> subWords = GameBoard.possibleWords;
		int defStart = 0;
		int defSize = 5;
		GameBoard.printWords(subWords, defStart, defSize);
	
		printWordAnalysisMenu();
		String cont = getUserResponse();
		
		while(cont.equals("1")||cont.equals("2")||cont.equals("3")){
			if(cont.equals("2")){
				subWords = GameBoard.possibleWords;
				defStart = 0;
				defSize = 5;
				GameBoard.printWords(subWords, defStart, defSize);
				
				printWordAnalysisMenu();
				cont = getUserResponse();
			}
			else{
				if(cont.equals("3")){
					defStart +=5;
					defSize +=5;
					GameBoard.printWords(subWords, defStart, defSize);
					
					System.out.println("\nPress 1 to search for a specific point \nPress 2 to go back to the " +
						"original menu, \nPress 3 to show the next 5 words" +
						"\nPress anything else to exit");
					cont = getUserResponse();
				}
				else{
					defStart = 0;
					defSize = 5;
		
					System.out.println("Enter column of position(x-value)");
					int column = Integer.parseInt(getUserResponse());
					
					System.out.println("Enter row of position(y-value)");
					int row = Integer.parseInt(getUserResponse());
			
					subWords = GameBoard.subWords(subWords,row,column);
					GameBoard.printWords(subWords, defStart, defSize);
		
					printWordAnalysisMenu();
					cont = getUserResponse();
				}
			}
		}
		
		System.out.println("\nGG!");
	}
	
	public static String getUserResponse(){
		Scanner scan = new Scanner(System.in);
		return scan.next();
	}
	
	public static void printWordAnalysisMenu(){
		
		System.out.println("\nPress 1 to search for a specific point \nPress 2 to go back to the " +
				"original menu, \nPress 3 to show the next 5 words" +
				"\nPress anything else to exit");
	}
}