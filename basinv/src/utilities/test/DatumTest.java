package utilities.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import utilities.Date;
import utilities.DatumException;


public class DatumTest {
	private Date datumString;
	private Date datumCopy;
	private Date datumInt;
	private Date datumDefault;

	@Before
	public void setUp() throws DatumException {
		datumString = new Date("21/09/2009");
		datumCopy = new Date(datumString);
		datumInt = new Date(21, 9, 2009);
		datumDefault = new Date();
	}

	@Test
	public void testToString() {
		// fail("Not yet implemented");
		System.out.println(datumDefault);
	}

	@Test
	public void testEquals() {
		// fail("Not yet implemented");
		assertTrue(datumString.equals(datumCopy));
		assertTrue(datumCopy.equals(datumInt));
	}

	@Test
	public void testCompareTo() {
		// fail("Not yet implemented");
		assertTrue(datumString.compareTo(datumCopy) == 0);
		assertTrue(datumCopy.compareTo(datumInt) == 0);
	}

	@Test
	public void testKleinerDan() {
		datumInt.setJaar(datumInt.getJaar() + 3);
		assertTrue(datumString.kleinerDan(datumInt));
	}

	@Test
	public void testVerschilInJaren() {
		datumInt.setJaar(datumInt.getJaar() + 3);
		assertTrue(datumInt.verschilInJaren(datumString) == 3);
	}

	@Test
	public void testVerschilInMaanden() {
		datumInt.setJaar(datumInt.getJaar() + 3);
		datumInt.setMaand(datumInt.getMaand() + 2);
		assertTrue(datumInt.verschilInMaanden(datumString) == 38);
	}

	@Test
	public void testIsLeap() {
		datumDefault.setJaar(2008);
		assertTrue(datumDefault.isLeapYear());
		datumDefault.setJaar(2012);
		assertTrue(datumDefault.isLeapYear());
		datumDefault.setJaar(2000);
		assertTrue(datumDefault.isLeapYear());
		datumDefault.setJaar(1900);
		assertFalse(datumDefault.isLeapYear());
		datumDefault.setJaar(2009);
		assertFalse(datumDefault.isLeapYear());
		datumDefault.setJaar(1800);
		assertFalse(datumDefault.isLeapYear());
		datumDefault.setJaar(2010);
		assertFalse(datumDefault.isLeapYear());
	}





	@Test
	public void testVerschilInDagen() {
		datumInt.setJaar(datumInt.getJaar() + 1);// 2010-09-21
		datumInt.setMaand(datumInt.getMaand() + 1);// 2010-10-21
		datumInt.setDag(datumInt.getDag() + 1);// 2010-10-22
		assertTrue(datumInt.verschilInDagen(datumString) == (365 + 30 + 1));
		// 2010-10-22 - 2009-09-21
		datumInt.setJaar(datumInt.getJaar() + 2);// 2012-10-22
		datumInt.setMaand(datumInt.getMaand() + 2);// 2012-12-22
		datumInt.setDag(datumInt.getDag() + 2);// 2012-12-24
		assertTrue(datumInt.verschilInDagen(datumString) == (365 + 30 + 1 + 365
				+ 366 + 31 + 30 + 2));
		// 2012-12-24 - 2009-09-21
	}

	@Test
	public void testAantalDagen() {
		assertTrue(datumDefault.aantalDagen() == 365); // 2011
		datumDefault.setJaar(datumDefault.getJaar() + 1);
		assertFalse(datumDefault.aantalDagen() == 365); // 2012
		datumDefault.setJaar(datumDefault.getJaar() + 1);
		assertTrue(datumDefault.aantalDagen() == 365); // 2013
		datumDefault.setJaar(datumDefault.getJaar() + 1);
		assertTrue(datumDefault.aantalDagen() == 365); // 2014
		datumDefault.setJaar(datumDefault.getJaar() + 1);
		assertTrue(datumDefault.aantalDagen() == 365); // 2015
		datumDefault.setJaar(datumDefault.getJaar() + 1);
		assertFalse(datumDefault.aantalDagen() == 365); // 2016
	}

	@Test
	public void testWijzigDatum() {
		assertTrue(datumDefault.setDatum(1, 1, 0001));
		assertTrue(datumDefault.setDatum(1, 1, 9999));
		assertTrue(datumDefault.setDatum(30, 6, 2009));
		assertTrue(datumDefault.setDatum(7, 1, 2009));
		assertTrue(datumDefault.setDatum(31, 10, 2009));
		assertTrue(datumDefault.setDatum(31, 1, 2009));
		assertTrue(datumDefault.setDatum(29, 2, 2008));

		assertFalse(datumDefault.setDatum(1, 1, 0000));
		assertFalse(datumDefault.setDatum(1, 14, 2009));
		assertFalse(datumDefault.setDatum(1, 41, 2009));
		assertFalse(datumDefault.setDatum(13, 71, 2008));
		assertFalse(datumDefault.setDatum(2, 29, 2009));
		assertFalse(datumDefault.setDatum(10, 31, 2009));
		assertFalse(datumDefault.setDatum(8, 0, 2009));
		assertFalse(datumDefault.setDatum(30, 2, 2008));

	}

	@Test
	public void testAmerikaansFormaat() {
		assertTrue(datumString.getDatumInAmerikaansFormaat().equals(
				datumString.getMaand() + Date.SEPARATOR + datumString.getDag()
						+ Date.SEPARATOR + datumString.getJaar()));
	}

	@Test
	public void testEuropeesFormaat() {
		assertTrue(datumInt.getDatumInEuropeesFormaat().equals(
				datumInt.getDag() + Date.SEPARATOR + datumInt.getMaand()
						+ Date.SEPARATOR + datumInt.getJaar()));
	}

	@Test
	public void testMaandTextFormaat() {
		assertTrue(datumInt.toString().equals(
				datumInt.getDag() + " " + datumInt.getMaandText() + " "
						+ datumInt.getJaar()));
	}

	@Test
	public void testLaatsteDagVanDeMaand() {
		Date laatste = null;

		try {
			Date test = new Date();
			for (int i = 0; i < 12; i++) {
				laatste = Date.laatsteDagVanDeMaand(test);
				test.veranderDatum(30);
				System.out.println(laatste);
			}
		} catch (DatumException e) {
			e.printStackTrace();
		}
	}

}
