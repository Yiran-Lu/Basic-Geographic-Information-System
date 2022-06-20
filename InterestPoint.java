package gis;
/**
 * the class that represents an interest point
 * @author Yiran Lu
 *
 * @param <M>
 */
public record InterestPoint<M>(Coordinate coordinate, M marker) {
	
	/**
	 * the method to validate an interest point by checking the marker
	 * @return if the interest point is valid
	 */
	public final InterestPoint<M> validate() {
		if(this.marker() == null)
			throw new NullPointerException();
		else
			return this;
	}
	
	/**
	 * the method to validate an interest point 
	 * @param interestPoint the interest point we are validating
	 * @return if the interest point is valid
	 */
	public static final InterestPoint<?> validate(InterestPoint<?> interestPoint) {
		if(interestPoint == null || interestPoint.validate() == null) {
			throw new NullPointerException();
		}
		else
			return interestPoint;
	}
	
	/**
	 * the method to check if the coordinate has the same marker as the input one
	 * @param marker the input marker
	 * @return if those two markers match with each other
	 */
	public boolean hasMarker(M marker) {
		return(this.marker().equals(marker));
	}
	
	/**
	 * the method to represent the interest point with string
	 * @return the string that represents the interest point
	 */
	public String toString() {
		return this.coordinate().toSimpleString() + this.marker();
	}

}
