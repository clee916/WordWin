
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		
		Scanner scan = new Scanner(System.in);
		int cont = 0;
		
		System.out.println("\nPress 1 or 2 to search for a specific point?");
		cont = scan.nextInt();

		while(cont == 1 || cont == 2){
			if(cont == 2){
				subWords = GameBoard.possibleWords;
			}
			
			Scanner scan2 = new Scanner(System.in);
			Scanner scan3 = new Scanner(System.in);
			
			System.out.println("Enter column of position(x-value)");
			int column = scan2.nextInt();
			
			System.out.println("Enter row of position(y-value)");
			int row = scan3.nextInt();
			
			subWords = subWords(subWords,row,column);
			
			GameBoard.analyzeWordsLength(subWords);
			GameBoard.analyzeWordsReach(subWords);
			GameBoard.analyzeWordsReachMin(subWords);	
			
			Scanner scan4 = new Scanner(System.in);
			System.out.println("\nPress 1 to compound search again, Press 2 to search for new point");
			cont = scan.nextInt();	
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