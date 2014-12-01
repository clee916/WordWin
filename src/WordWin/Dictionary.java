package WordWin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

public class Dictionary{
	
	public static final ArrayList<String> wordList = new ArrayList<String>();
	public ArrayList<String> subWordList = new ArrayList<String>();
	
	public Dictionary(){}
	
	public Dictionary(String fileName) throws IOException {
		
		FileReader inputFile = new FileReader(fileName);
		BufferedReader input = new BufferedReader(inputFile);
		
		String Line;
		
		while((Line = input.readLine())!=null)
			wordList.add(Line);
		
		input.close();
	}
	
	public ArrayList<String> getWordList(){
		return this.subWordList;
	}
	
	public Dictionary(String start, ArrayList<String> subDictionary){
	
		for(String word: subDictionary)
			if(word.startsWith(start.toLowerCase()))
				this.subWordList.add(word);
		
	}
	
	public boolean isWord(String word, ArrayList<String> subDictionary){
		return subDictionary.contains(word.toLowerCase());
	}
	
	public boolean wordStart(String start, ArrayList<String> subDictionary){
		
		for(String word: subDictionary){
			if(word.startsWith(start.toLowerCase()))
				return true;
		}
		return false;
	}
}
