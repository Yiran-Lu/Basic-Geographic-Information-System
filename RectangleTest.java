package gis;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class RectangleTest {

	@Test
	void testValidate() {
		Coordinate bl = new Coordinate(new BigDecimal("0"), new BigDecimal("0"));
		Coordinate tr = new Coordinate(new BigDecimal("1"), new BigDecimal("1"));
		Rectangle square = new Rectangle(bl, tr);
		assertEquals(square.validate(), square);
		Coordinate tr2 = new Coordinate(new BigDecimal("0"), new BigDecimal("1"));
		Rectangle line = new Rectangle(bl, tr2);
		assertEquals(line.validate(), line);
	}

	@Test
	void testValidateRectangle() {
		assertThrows(NullPointerException.class, () -> {Rectangle.validate(null); });
		Rectangle empty = new Rectangle(null, null);
		assertThrows(NullPointerException.class, () -> {Rectangle.validate(empty); });
		
		
		
	
	}

	@Test
	void testLeft() {
		Coordinate bl = new Coordinate(new BigDecimal("0"), new BigDecimal("0"));
		Coordinate tr = new Coordinate(new BigDecimal("1"), new BigDecimal("1"));
		Rectangle square = new Rectangle(bl, tr);
		assertEquals(square.left(), new BigDecimal("0"));
	}

	@Test
	void testRight() {
		Coordinate bl = new Coordinate(new BigDecimal("0"), new BigDecimal("0"));
		Coordinate tr = new Coordinate(new BigDecimal("1"), new BigDecimal("1"));
		Rectangle square = new Rectangle(bl, tr);
		assertEquals(square.right(), new BigDecimal("1"));
		
	}

	@Test
	void testTop() {
		Coordinate bl = new Coordinate(new BigDecimal("0"), new BigDecimal("0"));
		Coordinate tr = new Coordinate(new BigDecimal("1"), new BigDecimal("1"));
		Rectangle square = new Rectangle(bl, tr);
		assertEquals(square.top(), new BigDecimal("1"));
		
	}

	@Test
	void testBottom() {
		Coordinate bl = new Coordinate(new BigDecimal("0"), new BigDecimal("0"));
		Coordinate tr = new Coordinate(new BigDecimal("1"), new BigDecimal("1"));
		Rectangle square = new Rectangle(bl, tr);
		assertEquals(square.bottom(), new BigDecimal("0"));
	}

}
