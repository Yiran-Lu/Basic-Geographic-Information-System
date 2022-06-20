
package gis;
import java.math.BigDecimal;
/**
 * @author Yiran Lu
 * the class that represents a coordinate
 */
public record Coordinate(BigDecimal x, BigDecimal y) implements Comparable<Coordinate>{
	public static final Coordinate ORIGIN = new Coordinate(new BigDecimal("0"), new BigDecimal("0"));
	
	/**
	 * the method to validate a coordinate by checking the x and y value
	 * @return if the coordinate is valid
	 */
	public final Coordinate validate() {
		if(this.x() == null || this.y() == null) {
			throw new NullPointerException();
		}
		else
			return this;
	}
	
	/**
	 * the method to validate the coordinate
	 * @param coordinate the coordinate we are validating
	 * @return if the coordinate is valid
	 */
	public static final Coordinate validate(Coordinate coordinate) {
		if(coordinate == null || coordinate.validate() == null ) {
			throw new NullPointerException();
		}
		else 
			return coordinate;
	}
	
	@Override
	public int compareTo(Coordinate coordinate) {
		if((this.x().compareTo(coordinate.x()) == 0) && (this.y().compareTo(coordinate.y()) < 0))
			return -1;
		else if(this.x().compareTo(coordinate.x()) < 0)
				return -1;
		else if((this.x().compareTo(coordinate.x()) == 0) && (this.y().compareTo(coordinate.y()) == 0))
			return 0;
		else
			return 1;
		
	}
	
	/**
	 * the method to arrange a string for the coordinate
	 * @return the string that represents the coordinate
	 */
	public String toSimpleString() {
		return "[" + this.x() + "," + this.y() + "]";
	}
	

}
