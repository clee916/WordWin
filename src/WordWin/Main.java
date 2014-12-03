package WordWin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Comparators.LengthComparator;
import Comparators.ReachComparator;
import Comparators.ReachComparatorMin;
import Comparators.StartDepthComparator;
import Comparators.StartHeightComparator;

/**
 * Author: Cornell Lee
 * 
 * Generates potential playable words for user in MobileApp: WordBase
 */

public class Main{
	
	public static void main(String[] args){
		
		System.out.println("\nHello, welcome to WordWin, a cheat for WordBase, that geneartes all " +
				"possible playable words for you in WordBase. ");
		
		try {
			new Dictionary("bigDictionary.txt");
			System.out.println("\nDictionary successfully loaded!");
		} catch (IOException e1) {
			System.out.println("\nDictionary file not present, program will not work" +
					"\nTerminating....");
			return;
		}
		
		Scanner scan = new Scanner(System.in);
		ArrayList<GameBoard> loadedGames = new ArrayList<GameBoard>();
		
		printMainMenu();
		String options;
		options = scan.next().toLowerCase();
		
		while(!options.equals("quit")){
			
			if(options.equals("load")){
				
				System.out.println("\nEnter the file name of the board without the extension." +
						"\nEnsure that the board is formatted properly according to the README." +
						"\nAny loaded boards with the same name will be replaced.");
				String fileName = scan.next().toLowerCase()+".txt";
				try {
					System.out.println();
					GameBoard gameBoard = new GameBoard(fileName);
					if(!loadedGames.contains(gameBoard)){	
						int index = 0;
						while(index<loadedGames.size()){
							if(loadedGames.get(index).getIdentifer().equals(fileName.replaceAll(".txt", ""))){
								loadedGames.remove(loadedGames.get(index));
								System.out.print("Original loaded board has been replaced!\nNew Board ");
							}
							index++;
						}
						loadedGames.add(gameBoard);
						System.out.println("Successfully Loaded!");
					}else
						System.out.println("This board has already been loaded!");
				} catch (IOException e) {
					System.out.println("Invalid file name, cannot load board");
				}
			}
			
			if(options.equals("print")){
				
				boolean print = true;
				
				if(loadedGames.isEmpty())
					System.out.print("You have no loaded boards!\n");
				else{
					while(print){
						
						System.out.print("\nLoaded Games: ");
						for(GameBoard board: loadedGames)
							System.out.print(board.getIdentifer()+"  ");
						
						System.out.println("\nEnter board name to print");
						String gameBoard = scan.next().toLowerCase();
						GameBoard chosenBoard = new GameBoard();
						
						for(GameBoard board: loadedGames){
							if(board.getIdentifer().equals(gameBoard))
								chosenBoard = board;
						}
						
						if(chosenBoard.getIdentifer()==null){
							System.out.println("This board has not been loaded");
							System.out.println("Press 1 to print another board and anything else to cancel");
							if(!scan.next().toLowerCase().equals("1"))
								print = false;
						}
						else{
							chosenBoard.printGameBoard(chosenBoard.getBoard());
							System.out.println("Press 1 to print another board and anything else to cancel");
							if(!scan.next().toLowerCase().equals("1"))
								print = false;
						}
					}
				}
			}
			
			if(options.equals("find")){
				
				if(loadedGames.isEmpty()){
					System.out.print("You have no loaded boards!\n");
				}
				else{
					
					System.out.print("\nLoaded Games: ");
					for(GameBoard board: loadedGames)
						System.out.print(board.getIdentifer()+"  ");
					
					System.out.println("\nEnter board name to analyze");
					String gameBoard = scan.next().toLowerCase();
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
						ArrayList<Path> subWords = chosenBoard.getWords();
						int defStart = 0;
						int defSize = 5;
						chosenBoard.printWords(subWords, defStart, defSize);
					
						printWordAnalysisMenu();
						String cont = scan.next().toLowerCase();
					
						while(cont.equals("1")||cont.equals("2")||cont.equals("3")||cont.equals("4")){
							
							if(cont.equals("1")){
								defStart = 0;
								defSize = 5;
				
								System.out.println("Enter column of position(x-value)");
								int column = Integer.parseInt(scan.next());
							
								System.out.println("Enter row of position(y-value)");
								int row = Integer.parseInt(scan.next());
					
								subWords = chosenBoard.subWords(subWords,column,row);
								chosenBoard.printWords(subWords, defStart, defSize);
							}
							if(cont.equals("2")){
								subWords = chosenBoard.getWords();
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
								int toPrint = Integer.parseInt(scan.next());
								
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
							cont = scan.next().toLowerCase();	
						}		
					}
				}
			}	
			
			if(options.equals("win")){
				System.out.print("\nLoaded Games: ");
				for(GameBoard board: loadedGames)
					System.out.print(board.getIdentifer()+"  ");
				
				if(loadedGames.isEmpty()){
					System.out.print("You have no loaded boards!\n");
				}
				else{
					System.out.println("\nEnter board name to analyze in detail");
					String gameBoard = scan.next().toLowerCase();
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
						ArrayList<Path> playableWords = chosenBoard.userPlayableWords(chosenBoard.getWords());
						ArrayList<Path> winnableWords = chosenBoard.winningWords(chosenBoard.getWords(),
								chosenBoard.isBottom());
						ArrayList<Path> oppWinningWords = chosenBoard.winningWords(chosenBoard.getWords(),
								!chosenBoard.isBottom());
						
						printInDepthAnalysisMenu();
						String answer = scan.next().toLowerCase();
						while(!(answer.equals("menu")))
						{
							if(answer.equals("opponent")){
								Comparator<Path> comp;
								int defStart = 0;
								int defSize = 10;
								
								if(chosenBoard.isBottom())
									comp = new StartHeightComparator();
								else
									comp = new StartDepthComparator();
								chosenBoard.printWord(oppWinningWords, defStart, defSize, comp);
								System.out.println("\nPress show, to view all starting point of winning moves");
								if(scan.next().toLowerCase().equals("show")){
									Path startPoints = new Path(chosenBoard);
									for(Path path: oppWinningWords)
										startPoints.addPoint(path.getPath().get(0));
				
									startPoints.showOnBoard();
									answer = "opponent";
								}
							}
							if (answer.equals("user")){
								Comparator<Path> comp;
								int defStart = 0;
								int defSize = 10;
								
								if(chosenBoard.isBottom())
									comp = new StartDepthComparator();
								else
									comp = new StartHeightComparator();
								chosenBoard.printWord(winnableWords, defStart, defSize, comp);
								
								System.out.println("\nPress show, to view all starting point of winning moves");
								if(scan.next().toLowerCase().equals("show")){
									Path startPoints = new Path(chosenBoard);
									for(Path path: winnableWords)
										startPoints.addPoint(path.getPath().get(0));
									
									startPoints.showOnBoard();
								}
								System.out.println("\nPress future, to view playable words that leads to a" +
										"winning move next turn");
								if(scan.next().toLowerCase().equals("future")){
									for(Path path: winnableWords){
										Point winPoint = path.getPath().get(0);
										for(Path path2: playableWords){
											if(path2.hasPoint(winPoint.getRow(), winPoint.getCol())){
												System.out.println("\n"+path2.toString());
												System.out.println(path.toString());
											}
										}
									}
									System.out.println("\nWinnning moves has been printed");
								}
								answer = "user";
							}
							if(!answer.equals("opponent")&&!answer.equals("user")&&!answer.equals("menu"))
								System.out.println("\nInvalid Response");
							
							printInDepthAnalysisMenu();
							answer = scan.next().toLowerCase();
						}
					}
				}
			}
			if(!options.equals("load")&&!options.equals("print")&&!options.equals("find")
					&&!options.equals("quit")&&!options.equals("win")){
	
				System.out.println("Not a valid input");
			}
			printMainMenu();
			options = scan.next().toLowerCase();
		}
		scan.close();
		System.out.println("\nGood Bye! Thanks for using! :)");
	}

	public static void printMainMenu(){
		System.out.println("\nType load to load a gameboard.\nType print to view existing loaded gameboards." +
				"\nType find to find possible words on an existing gameboard." +
				"\nType win to do in depth analysis of the entire board.\nType quit to exit.");
	}
	
	public static void printWordAnalysisMenu(){
		System.out.println("\nPress 1 to search for a specific point out of these words." +
				" \nPress 2 to show original words.\nPress 3 to show the next 5 words." +
				"\nPress 4 to show a word on screen.\nPress anything else to exit.");
	}
	
	public static void printInDepthAnalysisMenu(){
		System.out.println("\nPress opponent to see winning words your opponent can play." +
				"\nPress user to see winning words that you can play" +
				"\nPress menu to return to the main menu");
	}
}