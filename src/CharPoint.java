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
		
		int i = this.point.getRow();
		int j = this.point.getCol();
		
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
}