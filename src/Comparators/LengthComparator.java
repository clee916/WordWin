package Comparators;

import WordWin.Path;

public class LengthComparator implements java.util.Comparator<Path> {

    public int compare(Path p1, Path p2) {
    	if(p1.getWord().length() > p2.getWord().length())
            return -1;

        if(p2.getWord().length() > p1.getWord().length())
            return 1;

        return p1.getWord().compareTo(p2.getWord());
    }
    
    @Override
    public String toString(){
    	return "longest";
    }
}