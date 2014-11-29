
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

public class Dictionary{
	
	protected static final ArrayList<String> wordList = new ArrayList<String>();
	
	public Dictionary(){}
	
	public Dictionary(String fileName) throws IOException {
		
		FileReader inputFile = new FileReader(fileName);
		BufferedReader input = new BufferedReader(inputFile);
		
		String Line;
		
		while((Line = input.readLine())!=null){
			wordList.add(Line);
		}
	}
	
	public static boolean isWord(String word){
		return wordList.contains(word.toLowerCase());
	}
	
	public static boolean wordStart(String start){
		
		for(String word: wordList){
			if(word.startsWith(start.toLowerCase()))
				return true;
		}
		
		return false;
	}
	
	
	
	public static List<String> wordList(String start){
		List<String> toReturn = new ArrayList<String>();
		
		for(String word: wordList){
			if(word.startsWith(start))
				toReturn.add(word);
		}
		
		return toReturn;
	}
	
}
