package gis;
import java.math.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class RectilinearRegion {
	
	public final Set<Rectangle> rectangles;
	
	
	public Set<Rectangle> getRectangles() {
		return this.rectangles;
	}
	
	private RectilinearRegion(Set<Rectangle> rectangles) {
		this.rectangles = new HashSet<Rectangle>();
		for(Rectangle rectangle : rectangles)
			this.rectangles.add(rectangle);
		if(this.isOverlapping())
			throw new NullPointerException();
	}
	
	public static final RectilinearRegion of(Set<Rectangle> rectangles) {
		RectilinearRegion rect = new RectilinearRegion(rectangles);
		if(rect.isOverlapping() == true)
			throw new NullPointerException();
		else return (new RectilinearRegion(rectangles));
		
	}
	
	/**
	 * method to get a copy of the rectangle
	 * @return
	 */
	private List<Rectangle> copy() {
		List<Rectangle> rec = new ArrayList<Rectangle>();
		for(Rectangle rectangle : this.rectangles)
			rec.add(rectangle);
		return rec;
		
	}
	
	
	public boolean isOverlapping() {
		boolean result = true;
		List<Rectangle> copy = this.copy();
		for(int i = 0; i < rectangles.size(); i++) {
			for(int j = i + 1; j < rectangles.size(); j++) {
				result = true; ///
				if(result != true)
					break;
			}
		}
	}
	
	
	
	public Collection<BigDecimal> xCoord() {
		List<BigDecimal> x = new ArrayList<BigDecimal>();
		for(Rectangle rectangle : this.rectangles) {
			x.add(rectangle.left());
			x.add(rectangle.right());
		}
		return x;
	}
	
	public Collection<BigDecimal> yCoord() {
		List<BigDecimal> y = new ArrayList<BigDecimal>();
		for(Rectangle rectangle : this.rectangles) {
			y.add(rectangle.top());
			y.add(rectangle.bottom());
		}
		return y;
	}
	
	
	
	public BiDimensionalMap<Rectangle> rectangleMap() {
		List<Coordinate> inside;
		BiDimensionalMap<Rectangle> map = new BiDimensionalMap<Rectangle>(this.xCoord(), this.yCoord());
		List<Coordinate> coordinate = map.coordinateSet();
		for(Rectangle rect : rectangles) {
			inside = rect.pointsInside(coordinate);
			map.addAnyWhere(rect, coordinate);
			
		}
		
		return map;
		
	}
	
	public boolean contained(Coordinate coordinate) {
		boolean result = false;
		for(Rectangle rectangle : rectangles) {
			if(rectangle.insideRec(coordinate)) {
				result = true;
			}
			else
				;
		}
		return result;
	}
	

}
