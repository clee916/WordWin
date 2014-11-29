import java.util.ArrayList;
import java.util.List;

public final class CharPoint{
	
	private Point point = null;	
	private String character = null;
	private boolean owned = false;
	
	public CharPoint(Point point, String character){
		
		this.point = point;
		this.character = character;
			
		if(Character.isUpperCase(character.charAt(0)))
			this.owned = true;
	}
	
	public String getChar(){
		return this.character;	
	}
	
	public Point getPoint(){
		return this.point;
	}

	public boolean getBool(){
		return this.owned;
	}
	
	public List<CharPoint> getNeighBors(){
		
		List<CharPoint> neighbors = new ArrayList<CharPoint>();
		
		int i = this.point.getRow();
		int j = this.point.getCol();
		
			neighbors.add(GameBoard.getCharPoint(i-1, j-1));
			neighbors.add(GameBoard.getCharPoint(i-1, j));
			neighbors.add(GameBoard.getCharPoint(i-1, j+1));
			neighbors.add(GameBoard.getCharPoint(i, j+1));
			neighbors.add(GameBoard.getCharPoint(i+1, j+1));
			neighbors.add(GameBoard.getCharPoint(i+1, j));
			neighbors.add(GameBoard.getCharPoint(i+1, j-1));
			neighbors.add(GameBoard.getCharPoint(i, j-1));
		
		return neighbors;

	}
	
	
	
	
}