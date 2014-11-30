public class ReachComparator implements java.util.Comparator<Path> {

    public int compare(Path p1, Path p2) {
    	
    	if(p1.getMaxRow() > p2.getMaxRow())
            return -1;

        if(p2.getMaxRow() > p1.getMaxRow())
            return 1;

        return p1.getWord().compareTo(p2.getWord());
    }
    
    @Override
    public String toString(){
    	return "highest";
    }
}