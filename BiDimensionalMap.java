package gis;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;

public final class BiDimensionalMap<T> {
	/**
	 * the points field that represents the points in the bi-dimensional map
	 */
	private final SortedMap<BigDecimal, SortedMap<BigDecimal, Collection<T>>> points = new TreeMap<BigDecimal, SortedMap<BigDecimal, Collection<T>>>();
	
	/**
	 * constructor of bi dimensional map
	 * @param xCoord
	 * @param yCoord
	 */
	BiDimensionalMap(Collection<BigDecimal> xCoord, Collection<BigDecimal> yCoord) {
		Updater updater = new Updater();
		for(BigDecimal horizontal : xCoord) {
			for(BigDecimal vertical : yCoord) {
				updater.setCoordinate(new Coordinate(horizontal, vertical)).set();
			}
		}
	}
	
	BiDimensionalMap() {
		
	}
	
	/**
	 * the method that adds the given value everywhere in the map
	 * @param value the value we want to add
	 */
	public final void addEverywhere(T value) {
		Updater updater = new Updater();
		for(Coordinate coordinate : this.coordinateSet()) {
			if(coordinate.validate() == coordinate) {
				updater.addValue(value).add();
			}
		}
	}
	
	public final void addAnyWhere(T value, List<Coordinate> list) {
		Updater updater = new Updater();
		for(Coordinate coordinate : list) {
			if(coordinate.validate() == coordinate) {
				updater.addValue(value).add();
			}
		}
		
	}
	
	
	/**
	 * the method to get the information at the given (x, y) coordinate
	 * @param x the x coordinate of the point
	 * @param y the y coordinate of the point
	 * @return the collection of information of that point
	 */
	public final Collection<T> get(BigDecimal x, BigDecimal y) {
		if(points.get(x) == null)
			return null;
		else
			return points.get(x).get(y);
	}
	
	/**
	 * the method to get the information at the given coordinate
	 * @param coordinate the coordinate of the point
	 * @return the collection of information of that point
	 */
	public final Collection<T> get(Coordinate coordinate) {
		if(points.get(coordinate.x()) == null)
			return null;
		else 
			return points.get(coordinate.x()).get(coordinate.y());
		
	}
	
	/**
	 * the method to return the updater
	 * @return the updater we return
	 */
	public final Updater getUpdater() {
		return new Updater();
	}
	
	
	
	
	
	
	/**
	 * the method to get the set of x values
	 * @return the set of x values
	 */
	public final Set<BigDecimal> xSet() {
		return points.keySet();
	}
	
	/**
	 * the method to get the set of y values
	 * @param x the x coordinate of y
	 * @return the set of y values at given x coordinate
	 */
	public final Set<BigDecimal> ySet(BigDecimal x) {
		return points.get(x).keySet();
	}
	
	/**
	 * the method to get a list of all coordinates
	 * @return the list that contains all the coordinate
	 */
	public final List<Coordinate> coordinateSet() {
		List<Coordinate> list = new ArrayList<Coordinate>();
		for(BigDecimal x : this.xSet()) {
			for(BigDecimal y : this.ySet(x)) {
				list.add(new Coordinate(x, y));
			}
		}
		return list;
		
	}
	
	/**
	 * the method to get a list of contents from points sorted by coordinates
	 * @return the list contains the information of coordinates
	 */
	public final List<Collection<T>> collectionList() {
		List<Collection<T>> list = new ArrayList<Collection<T>>();
		for(Coordinate coordinate : this.coordinateSet()) {
			list.add(this.get(coordinate));
		}
		return list;
	}
	
	/**
	 * the method to get the size of the collection in the map
	 * @return the size of the collection
	 */
	public final long collectionSize() {
		return this.collectionList().size();
	}
	
	/**
	 * the method to return the size of the collection that satisfy the predicate
	 * @param filter the predicate we will filter
	 * @return the size of the collection after we filter it
	 */
	public final long collectionSize(Predicate<? super T> filter) {
		Collection<T> list = new ArrayList<T>();
		for(Collection<T> collection : this.collectionList()) {
			for(T satisfy : collection) {
				if(filter.test(satisfy)) {
					list.add(satisfy);
				}
			}
		}
		return list.size();
		
		
	}
	
	/**
	 * the method to return the information of the map
	 * @return the brief information of the map
	 * waring this method is probably not correct
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(BigDecimal x : this.xSet()) {
			for(BigDecimal y : this.ySet(x)) {
				builder.append( "[" + x + "," + y + "]" + this.get(x, y));
			}	
		}
		return builder.toString();
	}
	
	/**
	 * the method to return the map that fits the required rectangle
	 * @param rectangle the rectangle that limits the points contained in the map
	 * @return the new map that fits the rectangle
	 * waring this method is probably not correct
	 */
	public final BiDimensionalMap<T> slice(Rectangle rectangle) {
		BiDimensionalMap<T> map = new BiDimensionalMap<T>();
		Updater updater = map.getUpdater();
		for(Coordinate coordinate : this.coordinateSet()) {
			if(coordinate.x().compareTo(rectangle.left()) == 1 && coordinate.x().compareTo(rectangle.right()) == -1 && coordinate.y().compareTo(rectangle.bottom()) == 1 && coordinate.y().compareTo(rectangle.top()) == -1) {
				updater.setCoordinate(coordinate);
				updater.setValues(this.get(coordinate));
				updater.add();
			}
				
		}
		
		return map;
	
	}
	
	
	
	public Collection<T> getElements(Collection<Coordinate> coordinate) {
		Collection<T> collection = new ArrayList<T>();
		for(Coordinate coord : coordinate)
			for(T entry : this.get(coord))
				collection.add(entry);
		return collection;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// inner class
	/**
	 * the updater class
	 * @author Yiran Lu
	 *
	 */
	public final class Updater {
		// fields to store x and y
		private BigDecimal x = new BigDecimal("0");
		private BigDecimal y = new BigDecimal("0");
		private Supplier<Collection<T>> collectionFactory = HashSet :: new;
		private Collection<T> values = collectionFactory.get();
		
		/**
		 * the method to set x
		 * @param x the x value we want to set
		 * @return return this updater
		 */
		public final Updater setX(BigDecimal x) {
			this.x = x;
			return this;
		}
		
		/**
		 * the method to set y
		 * @param y the y value we want to set
		 * @return return this updater
		 */
		public final Updater setY(BigDecimal y) {
			this.y = y;
			return this;
		}
		
		/**
		 * the method to set the collectionFactory
		 * @param set the input collectionFactory
		 * @return return the updater
		 */
		public final Updater setcollectionFactory(Supplier<Collection<T>> set) {
			this.collectionFactory = set;
			return this;
		}
		
		/**
		 * the method to set the values
		 * @param the values we want to set
		 * @return return the updater
		 */
		public final Updater setValues(Collection<T> collection) {
			this.values = collection;
			return this;
		}
		
		/**
		 * the method to set the x and y values to those of a valid coordinate
		 * @param coordinate the coordinate we want to set
		 * @return return the updater
		 */
		public final Updater setCoordinate(Coordinate coordinate) {
			setX(coordinate.x());
			setY(coordinate.y());
			return this;
		}
		
		/**
		 * the method to add a single value to the Updater¡¯s values
		 * @param value the value we want to add
		 * @return return the updater
		 */
		public final Updater addValue(T value) {
			this.values.add(value);
			return this;
		}
		
		/**
		 * the method to replace the information at the (x, y) location with values
		 * @return return the previous value
		 */
		public final Collection<T> set() {
			Collection<T> pre = get(x, y);
			if(points.get(x) == null) {
				TreeMap<BigDecimal, Collection<T>> map = new TreeMap<BigDecimal, Collection<T>>();
				points.put(x, map);
			}	
			points.get(x).put(y, values);
			return pre;
		}
		
		/**
		 * the method that adds all the values to the (x, y) location of the BiDimensionalMap
		 * @return return whether the interest points previously associated with location (x, y) changed because of this call
		 */
		public final boolean add() {
			// I searched online for this way to combine two collections
			Stream<T> combinedStream = Stream.of(values, points.get(x).get(y)).flatMap(Collection::stream);
			Collection<T> combined = combinedStream.collect(Collectors.toList());
			boolean isChanged = !points.get(x).get(y).equals(combined);
			if(points.get(x) == null) {
				TreeMap<BigDecimal, Collection<T>> map = new TreeMap<BigDecimal, Collection<T>>();
				points.put(x, map);
			}	
			points.get(x).put(y, combined);
			return isChanged;
	
		}
	}

}
