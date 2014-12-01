package Comparators;

import WordWin.Path;

public class ReachComparatorMin implements java.util.Comparator<Path> {

    public int compare(Path p1, Path p2) {
    	
    	if(p1.getMinRow() < p2.getMinRow())
            return -1;

        if(p2.getMinRow() < p1.getMinRow())
            return 1;

        return p1.getWord().compareTo(p2.getWord());
    }
    
    @Override
    public String toString(){
    	return "lowest";
    }
}