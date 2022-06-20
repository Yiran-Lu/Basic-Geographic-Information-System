package gis;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class CoordinateTest {

	@Test
	void testValidate() {
		//test if a valid coordinate could return itself
		Coordinate c1 = new Coordinate(new BigDecimal("1"), new BigDecimal("2"));
		assertEquals(c1.validate(), c1);
		Coordinate c2 = new Coordinate(new BigDecimal("0"), new BigDecimal("0"));
		assertEquals(c2.validate(), c2);
				
		//test if a null coordinate could return null pointer exception
		Coordinate c3 = new Coordinate(null, null);
		assertThrows(NullPointerException.class, () -> {c3.validate(); });
		
	}

	@Test
	void testValidateCoordinate() {
		//test if a valid coordinate could return itself
		Coordinate cvalid = new  Coordinate(new BigDecimal("1"), new BigDecimal("2"));
		assertEquals(Coordinate.validate(cvalid), cvalid);
		Coordinate cvalid2 = new  Coordinate(new BigDecimal("0"), new BigDecimal("0"));
		assertEquals(Coordinate.validate(cvalid2), cvalid2);
		// test if a null coordinate could return null pointer exception
		Coordinate cnull = new Coordinate(null, null);
		assertThrows(NullPointerException.class, () -> {Coordinate.validate(null); });
		//test if a coordinate with null x and y could return null pointer exception
		assertThrows(NullPointerException.class, () -> {Coordinate.validate(cnull); });
	}

	@Test
	void testCompareTo() {
		//test if two identical coordinate could return a 0
		Coordinate c1 = new  Coordinate(new BigDecimal("1"), new BigDecimal("2"));
		Coordinate c2 = new  Coordinate(new BigDecimal("1"), new BigDecimal("2"));
		assertEquals(c1.compareTo(c2), 0);
		//test if a coordinate is bigger than the other could return 1
		Coordinate small = new Coordinate(new BigDecimal("0"), new BigDecimal("0"));
		Coordinate big = new Coordinate(new BigDecimal("10"), new BigDecimal("10"));
		assertEquals(big.compareTo(small), 1);
		//test if a coordinate is smaller than the other when x1 < x2 and y1< y2
		assertEquals(small.compareTo(big), -1);
		//test if a coordinate is smaller than the other when x1 = x2 and y1 < y2
		Coordinate smaller = new Coordinate(new BigDecimal("10"), new BigDecimal("1"));
		assertEquals(smaller.compareTo(big), -1);
		
	}

}
