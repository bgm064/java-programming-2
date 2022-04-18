package yksikkotestaus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DayOfYearTest {

	@Test
	public void testFirstDayOfYear() {
		// tammikuun 1. päivä on vuoden 1. päivä
		int result = DayOfYear.dayOfYear(1, 1, 2022);
		assertEquals(1, result);
	}

	@Test
	public void testLastDayOfYear() {
		// joulukuun 31. päivä on vuoden 365. päivä
		int result = DayOfYear.dayOfYear(12, 31, 2022);
		assertEquals(365, result);
	}

	@Test
	public void testLastDayOfLeapYear() {
		// jos kyseessä on karkausvuosi, joulukuun 31. päivä on vuoden 366. päivä
		int result = DayOfYear.dayOfYear(12, 31, 2024);
		assertEquals(366, result);
	}

	@Test
	public void testInvalidDay() {
		String expectedMessage = "Invalid date";

		// päivä ei voi olla pienempi kuin 1.
		Exception underLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(1, 0, 2022);
		});
		assertTrue(underLimit.getMessage().contains(expectedMessage));

		// päivä ei voi olla suurempi kuin 31.
		Exception overLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(1, 32, 2022);
		});
		assertTrue(overLimit.getMessage().contains(expectedMessage));
	}

	@Test
	public void testInvalidMonth() {
		String expectedMessage = "Invalid date";

		// kuukausi ei voi olla pienempi kuin 1.
		Exception underLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(0, 1, 2022);
		});
		assertTrue(underLimit.getMessage().contains(expectedMessage));

		// kuukausi ei voi olla suurempi kuin 12.
		Exception overLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(13, 1, 2022);
		});
		assertTrue(overLimit.getMessage().contains(expectedMessage));
	}
	
	@Test
	public void testInvalidYear() {
		String expectedMessage = "Invalid date";
		
		// vuosi ei voi olla pienempi kuin 1.
		Exception underLimit = assertThrows(IllegalArgumentException.class, () -> {
			DayOfYear.dayOfYear(1, 1, 0);
		});
		assertTrue(underLimit.getMessage().contains(expectedMessage));
	}

}
