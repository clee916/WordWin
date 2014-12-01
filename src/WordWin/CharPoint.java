package WordWin;

import java.util.ArrayList;
import java.util.List;

public final class CharPoint{
	
	private Point point = null;	
	private String character = null;
	private GameBoard parent = new GameBoard();
	
	public CharPoint(Point point, String character){
		this.point = point;
		this.character = character;
	}
	
	public CharPoint(Point point, String character, GameBoard parent){
		this(point, character);
		this.parent = parent;
	}
	
	public String getChar(){
		return this.character;	
	}
	
	public Point getPoint(){
		return this.point;
	}
	
	public List<CharPoint> getNeighBors(){
		
		List<CharPoint> neighbors = new ArrayList<CharPoint>();
		
		int i = this.point.getCol();
		int j = this.point.getRow();
		
			neighbors.add(GameBoard.getCharPoint(i-1, j-1,parent.getBoard()));
			neighbors.add(GameBoard.getCharPoint(i-1, j,parent.getBoard()));
			neighbors.add(GameBoard.getCharPoint(i-1, j+1,parent.getBoard()));
			neighbors.add(GameBoard.getCharPoint(i, j+1,parent.getBoard()));
			neighbors.add(GameBoard.getCharPoint(i+1, j+1,parent.getBoard()));
			neighbors.add(GameBoard.getCharPoint(i+1, j,parent.getBoard()));
			neighbors.add(GameBoard.getCharPoint(i+1, j-1,parent.getBoard()));
			neighbors.add(GameBoard.getCharPoint(i, j-1,parent.getBoard()));
		
		return neighbors;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((character == null) ? 0 : character.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
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
		CharPoint other = (CharPoint) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}

}