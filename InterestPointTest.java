package gis;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class InterestPointTest {

	@Test
	void testValidate() {
		Coordinate c = new Coordinate(new BigDecimal("1"), new BigDecimal("1"));
		Coordinate c2 = new Coordinate(new BigDecimal("2"), new BigDecimal("2"));
		InterestPoint<String> p = new InterestPoint<String>(c, "RESIDENCE");
		InterestPoint<String> p2 = new InterestPoint<String>(c2, null);
		assertEquals(p.validate(), p);
		assertThrows(NullPointerException.class, () -> {p2.validate(); });
	}

	@Test
	void testValidateInterestPointOfQ() {
		Coordinate c3 = new Coordinate(new BigDecimal("3"), new BigDecimal("3"));
		InterestPoint<String> p3 = new InterestPoint<String>(c3, null);
		assertThrows(NullPointerException.class, () -> {InterestPoint.validate(null); });
		assertThrows(NullPointerException.class, () -> {InterestPoint.validate(p3); });
		
	}

	@Test
	void testHasMarker() {
		Coordinate c4 = new Coordinate(new BigDecimal("4"), new BigDecimal("4"));
		InterestPoint<String> p4 = new InterestPoint<String>(c4, "RESIDENCE");
		assertEquals(p4.hasMarker("RESIDENCE"), true);
		assertEquals(p4.hasMarker("DINNINGHALL"), false);
		
	}

}
