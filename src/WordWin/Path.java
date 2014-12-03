package WordWin;

import java.util.ArrayList;

public final class Path{
	
	private String word;
	private ArrayList<Point> path = new ArrayList<Point>();
	private ArrayList<CharPoint> gameBoard = new ArrayList<CharPoint>();

	public Path (String word, ArrayList<Point> path, ArrayList<CharPoint> gameBoard){
		this.word = word;
		this.path = path;
		this.gameBoard = gameBoard;
	}

	public Path(GameBoard gameBoard) {
		this.gameBoard = gameBoard.getBoard();
	}

	public String getWord(){
		return this.word;
	}
	
	public void replaceWord(String toReplace){
		this.word = toReplace;
	}
	
	public ArrayList<Point> getPath(){
		return this.path;
	}
	
	public void addPoint(Point toAdd){
		this.path.add(toAdd);
	}

	public int getMaxRow(){
		int max = 0;
		
		for(Point point: path){
			if(point.getRow()>max)
				max = point.getRow();
		}
		return max;
	}
	
	public int getMinRow(){
		int min = 12;
		
		for(Point point: path){
			if(point.getRow()<min)
				min = point.getRow();
		}
		return min;
	}
	
	public boolean hasRow(int row){
		
		for(Point point: path){
			if(point.getRow()==row)
				return true;
		}
		return false;
	}
	
	public boolean hasPoint(int row, int column){
		
		for(Point point:path){
			if(point.getRow()==row&&point.getCol()==column)
				return true;
		}
		
		return false;
	}
	
	public void showOnBoard(){
		for (int j = 12; j >=0;j--){
			for (int i = 0; i < 10; i++){
				
				if(path.contains(new Point(i,j)))
					System.out.print(GameBoard.getCharPoint(i,j,gameBoard).getChar());
				else
					System.out.print(".");
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
	
	@Override
	public String toString(){
		String toReturn = word;
		
		for(int index = 0; index < path.size();index++){
			toReturn = toReturn.concat(path.get(index).toString());
		}
		return toReturn;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Path))
			return false;

		Path other = (Path) obj;
		if (word.equals(other.getWord()) && path.equals(other.getPath()))
			return true;
		
		return false;
	}
}