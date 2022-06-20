package gis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * the class that represents a rectangle
 * @author luyir
 *
 */
public record Rectangle(Coordinate bottomLeft, Coordinate topRight) {
	/**
	 * the method that ensures that the coordinates are valid
	 * @return the rectangle
	 */
	public final Rectangle validate() {
		if(bottomLeft.validate() == null && topRight.validate() == null)
			throw new NullPointerException();
		else if(topRight.compareTo(bottomLeft) == -1)
			throw new NullPointerException();
		else
			return this; 
	}
	
	/**
	 * the method to validate the rectangle
	 * @param rectangle the input rectangle
	 * @return the rectangle
	 */
	public static final Rectangle validate(Rectangle rectangle) {
		if(rectangle == null || rectangle.validate() == null)
			throw new NullPointerException();
		else
			return rectangle;
	}
	
	/**
	 * the method to get the left coordinate
	 * @return the left coordinate
	 */
	public final BigDecimal left() {
		return this.bottomLeft.x();
	}
	
	/**
	 * the method to get the right coordinate
	 * @return the right coordinate
	 */
	public final BigDecimal right() {
		return this.topRight.x();
	}
	
	/**
	 * the method to get the top coordinate
	 * @return the top coordinate
	 */
	public final BigDecimal top() {
		return this.topRight.y();
	}
	
	/**
	 * the method to get the bottom coordinate
	 * @return the left coordinate
	 */
	public final BigDecimal bottom() {
		return this.bottomLeft.y();
	}
	
	/**
	 * the method to give a brief description of the rectangle
	 * @return return a brief description of the rectangle
	 */
	public String toString() {
		return this.bottomLeft.toSimpleString() + this.topRight.toSimpleString();
	}
	
	
	// 
	public boolean insideRec(Coordinate coordinate) {
		boolean result = true;
		if(coordinate.x().compareTo(this.left()) == 1 && coordinate.x().compareTo(this.right()) == -1 && coordinate.y().compareTo(this.bottom()) == 1 && coordinate.y().compareTo(this.top()) == -1) {
			return true;
		}
		else
			result = false;
		
		return result;
	}
	
	public List<Coordinate> pointsInside(List<Coordinate> list) {
		List<Coordinate> result = new ArrayList<Coordinate>();
		for(Coordinate coordinate : list) {
			if(this.insideRec(coordinate) == true)
				result.add(coordinate);
			else
				;
		}
		return result;	
	}
	
	
	

}
