package WordWin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Comparators.LengthComparator;
import Comparators.ReachComparator;
import Comparators.ReachComparatorMin;

public final class GameBoard{
	
	private String identifier = null;
	private boolean bottomBase = true;
	private ArrayList<CharPoint> gameBoard = new ArrayList<CharPoint>();
	private ArrayList<CharPoint> ownedChar = new ArrayList<CharPoint>();
	private ArrayList<Path> possibleWords = new ArrayList<Path>();
	
	public GameBoard(){}
	
	public GameBoard(String fileName) throws IOException{
		this.identifier = fileName.replaceFirst(".txt", "");
		String boardChar = readFile(fileName);
		
		if(boardChar.length()!=130){
			System.out.println(boardChar.length());
			System.out.println("Board is not formatted correctly for a WordBase game");
			throw new IOException();
		}
		
		char[] charArray = boardChar.toCharArray();
		
		int j = 12;
		int k = 0;
		
		while( j >= 0){
			int i = 0;
			
			while(i<10){
				char character = charArray[k];
				Point point = new Point(i,j);
				CharPoint charPoint = new CharPoint(point,Character.toString(character),this);
				this.gameBoard.add(charPoint);
				
				if(Character.isUpperCase(character))
					this.ownedChar.add(charPoint);
		
				i++;
				k++;		
			}
			j--;
		}
		if(this.ownedChar.get(0).getPoint().equals(new Point(0,12)))
			this.bottomBase = false;
	}
	
	public ArrayList<CharPoint> getBoard(){
		return this.gameBoard;
	}
	
	public String getIdentifer(){
		return this.identifier;
	}
	
	public boolean isBottom(){
		return this.bottomBase;
	}
	
	public ArrayList<Path> getWords(){
		return this.possibleWords;
	}
	
	public static CharPoint getCharPoint(int i, int j, ArrayList<CharPoint> gameBoard){
		Point toFind = new Point(i,j);
		
		for(CharPoint charPoint: gameBoard)
			if(charPoint.getPoint().equals(toFind))
				return charPoint;
		
		CharPoint test = new CharPoint(toFind, "!");
		return test;
	}
	
	public void printGameBoard(ArrayList<CharPoint> gameBoard){
		for (int j = 12; j >=0; j--){
			for (int i = 0; i < 10; i++){
				System.out.print(getCharPoint(i,j,gameBoard).getChar());			
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
	
	public void analyzeGameBoard(){
		possibleWords.clear();
		String current = null;

		for(CharPoint charPoint: ownedChar){
			
			Dictionary subDictionary1 = new Dictionary(charPoint.getChar(), Dictionary.wordList);
			
			ArrayList<Point> used = new ArrayList<Point>();
			used.add(charPoint.getPoint());
			current = charPoint.getChar();

			analyzeNeighbors(charPoint,current,used,subDictionary1);
		}
	}
	
	public void analyzeNeighbors(CharPoint start, String current, 
			ArrayList<Point> used, Dictionary subDictionary){
		
		for(CharPoint neighbor: start.getNeighBors()){
			ArrayList<Point> usedClone = new ArrayList<Point>(used);
			Path path = new Path(current,usedClone,gameBoard);
			boolean cont = true;
		
			if(usedClone.contains(neighbor.getPoint()))
				cont = false;
			
			String currentPlus = current.concat(neighbor.getChar());
			
			if(!(subDictionary.wordStart(currentPlus,subDictionary.getWordList())))
				cont = false;
			
			if(cont){
				
				usedClone.add(neighbor.getPoint());
				path.replaceWord(currentPlus);
				
				if(subDictionary.isWord(path.getWord(),subDictionary.getWordList())&&
						!(possibleWords.contains(path)))
					possibleWords.add(path);
				
				Dictionary subDictionary1 = new Dictionary(currentPlus,subDictionary.getWordList());
				analyzeNeighbors(neighbor,currentPlus,usedClone,subDictionary1);
			}
		}
	}
		
	public void printWord(ArrayList<Path> subWords, int start, int maxSize, Comparator<Path> comp){	
		System.out.println("\nYour "+comp.toString()+" Words");
		Collections.sort(subWords, comp);
		
		if(subWords.size()<maxSize)
			maxSize = subWords.size();
		
		for (int index = start; index < maxSize ; index++){
			Path path = subWords.get(index);
			System.out.print(path.getWord());
			
			for(Point point: path.getPath())
				System.out.print(point.toString());
			
			System.out.println();
		}
	}
	
	public void printWords(ArrayList<Path> subWords, int start, int maxSize){
		Comparator<Path> length = new LengthComparator();
		Comparator<Path> height = new ReachComparator();
		Comparator<Path> depth = new ReachComparatorMin();
		
		printWord(subWords, start, maxSize, length);
		
		if(bottomBase)
			printWord(subWords, start, maxSize, height);
		else
			printWord(subWords, start, maxSize, depth);
	}

	public final String readFile(String fileName) throws IOException {
		
		FileReader inputFile = new FileReader(fileName);
		BufferedReader input = new BufferedReader(inputFile);
		
		String toReturn = "";
		String Line;
		
		while((Line = input.readLine())!=null){
			toReturn = toReturn.concat(Line);
		}
		
		input.close();
		return toReturn.replaceAll("\\s", "");
	}
	
	public ArrayList<Path> subWords(ArrayList<Path> moreWords, int row, int column){
		
		ArrayList<Path> toReturn = new ArrayList<Path>();
		
		for(Path path: moreWords)
			if(path.getPath().contains(new Point(row,column)))
				toReturn.add(path);
		
		return toReturn;
	}

	public void analyzeEntireGameBoard(){
		possibleWords.clear();
		String current = null;
		
		for(CharPoint charPoint: gameBoard){
			
			Dictionary subDictionary = new Dictionary(charPoint.getChar(), Dictionary.wordList);
			
			ArrayList<Point> used = new ArrayList<Point>();
			used.add(charPoint.getPoint());
			current = charPoint.getChar();
			
			analyzeNeighbors(charPoint,current,used,subDictionary);
		}
	}
	
	public ArrayList<Path> userPlayableWords(ArrayList<Path> possibleWords){
		ArrayList<Path> toReturn = new ArrayList<Path>();
		
		ArrayList<Point> validStartPos = new ArrayList<Point>();
		
		for(CharPoint hi: ownedChar){
			validStartPos.add(hi.getPoint());
		}
		
		for(Path path: possibleWords){
			if(validStartPos.contains(path.getPath().get(0)))
				toReturn.add(path);
		}
		return toReturn;
	}
	
	public ArrayList<Path> winningWords(ArrayList<Path> possibleWords,boolean botBase){
		ArrayList<Path> toReturn = new ArrayList<Path>();
		
		if(botBase){
			for(Path path: possibleWords){
				if(path.hasRow(12))
					toReturn.add(path);
			}
		}else{
			for(Path path: possibleWords){
				if(path.hasRow(0))
					toReturn.add(path);
			}
		}
		return toReturn;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result
				+ ((ownedChar == null) ? 0 : ownedChar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameBoard other = (GameBoard) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		if (ownedChar == null) {
			if (other.ownedChar != null)
				return false;
		} else if (!ownedChar.equals(other.ownedChar))
			return false;
		return true;
	}

}