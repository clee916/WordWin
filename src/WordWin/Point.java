package WordWin;

public final class Point{
	
	private int column;
	private int row;
	
	public Point(int x, int y){
		this.column = x;
		this.row = y;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.column;
	}
	
	@Override
	public String toString(){
		String toReturn = "(" + column + " , "+ row + ")";
		return toReturn;
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Point))
			return false;

		Point other = (Point) obj;
		if (row == other.getRow() && column == other.getCol())
			return true;
		
		return false;
	}
	
	public int hashcode(){
		return  row*column;
	}
}