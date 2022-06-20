package gis;
import java.util.*;


public final class InterestPoints<M> {
	private final BiDimensionalMap<InterestPoint<?>> points;
	
	/**
	 * the constructor of the InterestPoints class
	 * @param builder the builder of interestPoints
	 */
	private InterestPoints(Builder builder) {
		points = builder.points;
	}
	
	
	/**
	 * the method returns the interest points at the given valid coordinate
	 * @param coordinate the input coordinate
	 * @return the interest points on that coordinate
	 */
	public final Collection<InterestPoint<?>> get(Coordinate coordinate) {
		coordinate.validate();
		return points.get(coordinate);
	}
	
	/**
	 * the method to get the list of interest points ordered by their coordinates
	 * @return the list that contains the points
	 */
	public final List<Collection<InterestPoint<?>>> interestPoints() {
		List<Collection<InterestPoint<?>>> list = new ArrayList<Collection<InterestPoint<?>>>();
		for(Coordinate coordinate : points.coordinateSet())
			list.add(points.get(coordinate));
		return list;

	}
	
	/**
	 * the method to return a brief description of the interest points
	 * @return brief description of interest points
	 * waring this method is probably not correct
	 */
	public String toString() {
		return this.interestPoints().toString();
	}
	
	
	public final long count(RectilinearRegion region, M marker) {
		long count = 0;
		Collection<Coordinate> collection = new ArrayList<Coordinate>();
		for(Coordinate coordinate : points.coordinateSet())
			if(region.contained(coordinate) == true)
				collection.add(coordinate);
		for(InterestPoint point : points.getElements(collection))
			if(point.hasMarker(marker) == true)
				count ++;
		return count;
		}
	
	
	
	
	/**
	 * the builder class
	 * @author Yiran Lu
	 *
	 */
	public static class Builder {
		private final BiDimensionalMap<InterestPoint<?>> points = new BiDimensionalMap<InterestPoint<?>>();
		
		
		/**
		 * the method to add a valid interest point to the map
		 * @param interestPoint the point we want to add
		 * @return if the value is added
		 */
		public final boolean add(InterestPoint<?> interestPoint) {
			interestPoint.validate();
			Collection<InterestPoint<?>> collection = new ArrayList<InterestPoint<?>>();
			collection.add(interestPoint);
			points.getUpdater().setCoordinate(interestPoint.coordinate()).setValues(collection).add();
			return true;
		}
		
		
		/**
		 * the method to help build the builder
		 * @return the new interest points
		 */
		public final InterestPoints build() {
			return new InterestPoints(this);
		}
		
	}

}
