
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Author: Cornell Lee
 * 
 * Generates potential playable words for user in MobileApp: WordBase
 */

public class main{
	public static void main(String args[]) throws IOException {
		
		Dictionary dict = new Dictionary("dictionary.txt");
		
		GameBoard gameBoard = null;
		
		System.out.println("Enter the file name where the board is stored without extension");
		Scanner boardScanner = new Scanner(System.in);
		String fileName = boardScanner.nextLine()+".txt";
		
		String userBoard = readFile(fileName);

		gameBoard = new GameBoard(userBoard);
		GameBoard.analyzeGameBoard();
		
		List<Path> subWords = GameBoard.possibleWords;
		int defStart = 0;
		int defSize = 5;
		
		GameBoard.analyzeWordsLength(subWords,defStart,defSize);
		GameBoard.analyzeWordsReach(subWords,defStart,defSize);
		GameBoard.analyzeWordsReachMin(subWords,defStart,defSize);
		
		Scanner scan = new Scanner(System.in);
		int cont = 0;
		
		System.out.println("\nPress 1 to search for a specific point, 3 to show more words");
		cont = scan.nextInt();

		while(cont == 1 || cont == 2 || cont == 3){
			if(cont == 2){
				subWords = GameBoard.possibleWords;
				GameBoard.analyzeWordsLength(subWords, 0,5);
				GameBoard.analyzeWordsReach(subWords,0,5);
				GameBoard.analyzeWordsReachMin(subWords,0,5);	
				System.out.println("\nPress 1 to search for a specific point, 3 to show more words");
				cont = scan.nextInt();
			}
			else{
				if(cont == 3){
					defStart = defStart+5;
					defSize = defSize+5;
					GameBoard.analyzeWordsLength(subWords, defStart,defSize);
					GameBoard.analyzeWordsReach(subWords,defStart,defSize);
					GameBoard.analyzeWordsReachMin(subWords,defStart,defSize);	
					System.out.println("\nPress 1 to search for a specific point, 3 to show more words");
					cont = scan.nextInt();
				}
				else{
					defStart = 0;
					defSize = 5;
		
					Scanner scan2 = new Scanner(System.in);
					Scanner scan3 = new Scanner(System.in);
					
					System.out.println("Enter column of position(x-value)");
					int column = scan2.nextInt();
					
					System.out.println("Enter row of position(y-value)");
					int row = scan3.nextInt();
			
					subWords = subWords(subWords,row,column);
			
					GameBoard.analyzeWordsLength(subWords, defStart,defSize);
					GameBoard.analyzeWordsReach(subWords,defStart,defSize);
					GameBoard.analyzeWordsReachMin(subWords,defStart,defSize);	
			
					Scanner scan4 = new Scanner(System.in);
					System.out.println("\nPress 1 to compound search, Press 2 to show original options, " +
							"Press 3 to show more words");
			
					cont = scan.nextInt();
				}
			}
		}
		
		System.out.println("\nGG!");
	}
	
		public final static List<Path> subWords(List<Path> moreWords, int row, int column){
			
			List<Path> toReturn = new ArrayList<Path>();
			
			for(Path path: moreWords)
				if(path.getPath().contains(new Point(row,column)))
					toReturn.add(path);
			
			return toReturn;
	}
		
		public final static String readFile(String fileName) throws IOException {
			
			FileReader inputFile = new FileReader(fileName);
			BufferedReader input = new BufferedReader(inputFile);
			
			String toReturn = "";
			
			String Line;
			
			while((Line = input.readLine())!=null){
				toReturn = toReturn.concat(Line);
			}
			
			return toReturn;
		}
}