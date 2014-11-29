
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

public class Dictionary{
	
	protected static final ArrayList<String> wordList = new ArrayList<String>();
	public static ArrayList<String> subWordList = new ArrayList<String>();
	
	public Dictionary(String fileName) throws IOException {
		
		FileReader inputFile = new FileReader(fileName);
		BufferedReader input = new BufferedReader(inputFile);
		
		String Line;
		
		while((Line = input.readLine())!=null){
			wordList.add(Line);
		}
		subWordList = (ArrayList<String>) wordList.clone();
	}
	
	public static void subDictinonary(String start){
		subWordList.clear();
		for(String word: wordList){
			if(word.startsWith(start.toLowerCase()))
				subWordList.add(word);
		}
	}
	
	public static boolean isWord(String word){
		return subWordList.contains(word.toLowerCase());
	}
	
	public static boolean wordStart(String start){
		
		for(String word: subWordList){
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
