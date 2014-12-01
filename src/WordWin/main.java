package WordWin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Comparators.LengthComparator;
import Comparators.ReachComparator;
import Comparators.ReachComparatorMin;

/**
 * Author: Cornell Lee
 * 
 * Generates potential playable words for user in MobileApp: WordBase
 */

public class main{
	public static void main(String args[]){
		
		try {
			new Dictionary("bigDictionary.txt");
			System.out.println("\nDictionary successfully loaded!");
		} catch (IOException e1) {
			System.out.println("Dictionary file not present, program will not work");
		}
	
		ArrayList<GameBoard> loadedGames = new ArrayList<GameBoard>();

		System.out.println("\nHello, welcome to WordWin, a cheat for WordBase, that geneartes all " +
				"possible playable words for you in WordBase. \nTo get started, load a board");
		
		String options;
		
		printMainMenu();
		options = getUserResponse();
		
		while(!options.equals("quit")){
			
			if(options.equals("load")){
				
				System.out.println("\nEnter the file name of the board without the extension" +
						"\nEnsure that the board is formatted properly according to the ReadMe" +
						"\nIf you have editted a loaded board, please rename it");
				String fileName = getUserResponse()+".txt";
				try {
					GameBoard gameBoard = new GameBoard(fileName);
					if(!loadedGames.contains(gameBoard)){
						loadedGames.add(gameBoard);
						System.out.println("Successfully loaded!");
					}else
						System.out.println("This board has already been loaded!");
				} catch (IOException e) {
					System.out.println("Invalid file name, cannot load board");
				}
				
				printMainMenu();
				options = getUserResponse();
			}
			
			if(options.equals("print")){
				
				boolean print = true;
				
				System.out.print("\nLoaded Games: ");
				for(GameBoard board: loadedGames)
					System.out.print(board.getIdentifer()+"  ");
				
				if(loadedGames.isEmpty())
					System.out.print("You have no loaded boards!\n");
				else{
					while(print){
						System.out.println("\nEnter board name to print");
						String gameBoard = getUserResponse();
						GameBoard chosenBoard = new GameBoard();
						
						for(GameBoard board: loadedGames){
							if(board.getIdentifer().equals(gameBoard))
								chosenBoard = board;
						}
						
						if(chosenBoard.getIdentifer()==null){
							System.out.println("This board has not been loaded");
							System.out.println("Press 1 to print another board and anything else to cancel");
							if(!getUserResponse().equals("1"))
								print = false;
						}
						else{
							chosenBoard.printGameBoard(chosenBoard.getBoard());
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
				System.out.print("\nLoaded Games: ");
				for(GameBoard board: loadedGames)
					System.out.print(board.getIdentifer()+"  ");
				
				if(loadedGames.isEmpty()){
					System.out.print("You have no loaded boards!\n");
				}
				else{
					System.out.println("\nEnter board name to analyze");
					String gameBoard = getUserResponse();
					GameBoard chosenBoard = new GameBoard();
					
					for(GameBoard board: loadedGames){
						if(board.getIdentifer().equals(gameBoard))
							chosenBoard = board;
					}
					
					if(chosenBoard.getIdentifer()==null){
						System.out.println("This board has not been loaded");
						
					}
					else{
						chosenBoard.analyzeGameBoard();
						ArrayList<Path> subWords = GameBoard.possibleWords;
						int defStart = 0;
						int defSize = 5;
						chosenBoard.printWords(subWords, defStart, defSize);
					
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
								chosenBoard.printWords(subWords, defStart, defSize);
							}
							if(cont.equals("2")){
								subWords = GameBoard.possibleWords;
								defStart = 0;
								defSize = 5;
								chosenBoard.printWords(subWords, defStart, defSize);
							}
							if(cont.equals("3")){
								defStart +=5;
								defSize +=5;
								chosenBoard.printWords(subWords, defStart, defSize);
							}
							if(cont.equals("4")){
								System.out.println("Enter (0-9) to print the respective shown word (0 is top)" +
										" and anything else to cancel");
								int toPrint = Integer.parseInt(getUserResponse());
								
								if(toPrint>=0&&toPrint<5){
									Collections.sort(subWords, new LengthComparator());
									subWords.get(defStart+toPrint).showOnBoard();
								}
								if(toPrint>4&&toPrint<10){
			
									if(chosenBoard.isBottom()){
										Collections.sort(subWords, new ReachComparator());
										subWords.get(defStart+toPrint-5).showOnBoard();
									}else{
										Collections.sort(subWords, new ReachComparatorMin());
										subWords.get(defStart+toPrint-5).showOnBoard();
									}
								}
							}
							printWordAnalysisMenu();
							cont = getUserResponse();	
						}		
					}
				}
				printMainMenu();
				options = getUserResponse();
			}	
			
			if(options.equals("win")){
				System.out.print("\nLoaded Games: ");
				for(GameBoard board: loadedGames)
					System.out.print(board.getIdentifer()+"  ");
				
				if(loadedGames.isEmpty()){
					System.out.print("You have no loaded boards!\n");
				}
				else{
					System.out.println("\nEnter board name to find win condition");
					String gameBoard = getUserResponse();
					GameBoard chosenBoard = new GameBoard();
					
					for(GameBoard board: loadedGames){
						if(board.getIdentifer().equals(gameBoard))
							chosenBoard = board;
					}
					
					if(chosenBoard.getIdentifer()==null){
						System.out.println("This board has not been loaded");
						
					}
					else{
						chosenBoard.analyzeEntireGameBoard();
						ArrayList<Path> playableWords = chosenBoard.userPlayableWords(GameBoard.possibleWords);
						ArrayList<Path> winnableWords = chosenBoard.winNext(GameBoard.possibleWords);
						
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
						System.out.println("\nWinnning moves printed in random order");
					}
				}
				printMainMenu();
				options = getUserResponse();
			}
			if(!options.equals("load")&&!options.equals("print")&&!options.equals("find")
					&&!options.equals("quit")&&!options.equals("win")){
				
				System.out.println("Not a valid input");
				printMainMenu();
				options = getUserResponse();
			}
		}
		System.out.println("\nGood Bye! Thanks for using! :)");
	}
	
	public static String getUserResponse(){
		Scanner scan = new Scanner(System.in);
		return scan.next();
	}
	
	public static void printMainMenu(){
		System.out.println("\nType load, to load a gameboard.\nType print, to view existing loaded gameboards" +
				"\nType find, to find possible words on an existing gameboard" +
				"\nType win, to show moves that can win in two moves\nType quit to exit");
	}
	
	public static void printWordAnalysisMenu(){
		
		System.out.println("\nPress 1 to search for a specific point \nPress 2 to show original words " +
				"\nPress 3 to show the next 5 words\nPress 4 to show a word on screen" +
				"\nPress anything else to exit");
	}
}