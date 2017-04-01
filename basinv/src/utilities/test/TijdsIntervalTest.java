package utilities.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import utilities.TijdsInterval;

public class TijdsIntervalTest {
	private TijdsInterval first;
	private TijdsInterval second;
	private TijdsInterval third;
	private TijdsInterval fourth;
	@Before
	public void setUp() throws Exception {
		GregorianCalendar van1 = new GregorianCalendar();
		GregorianCalendar tot1 = new GregorianCalendar();
		GregorianCalendar van2 = new GregorianCalendar();
		GregorianCalendar tot2 = new GregorianCalendar();
		GregorianCalendar van3 = new GregorianCalendar();
		GregorianCalendar tot3 = new GregorianCalendar();
		GregorianCalendar van4 = new GregorianCalendar();
		GregorianCalendar tot4 = new GregorianCalendar();

		
		tot1.add(Calendar.HOUR, 1);		
		tot2.add(Calendar.DATE, 1);
		tot2.add(Calendar.HOUR, 1);
		van3.add(Calendar.HOUR, -1);
		tot3.add(Calendar.HOUR, 2);
		van4.add(Calendar.HOUR, -2);
		tot4.add(Calendar.HOUR, -1);
		
		first = new TijdsInterval(van1, tot1);
		second = new TijdsInterval(van2, tot2);
		third = new TijdsInterval(van3, tot3);
		fourth = new TijdsInterval(van4, tot4);
	}

	@Test
	public void testGetLengteInDagen() {
		assertTrue(first.getLengteInDagen()==0);
		assertTrue(second.getLengteInDagen()==1);
	}
	@Test
	public void testGetLengteInUren() {
		assertTrue(first.getLengteInUren()==1);
		assertTrue(second.getLengteInUren()==25);
		assertTrue(third.getLengteInUren()==3);
	}
	@Test
	public void testGetLengteInMinuten() {
		assertTrue(first.getLengteInMinuten()==60);
		assertTrue(second.getLengteInMinuten()==(60 * 25));
	}	
	@Test
	public void testGetLengteInSeconden() {
		assertTrue(first.getLengteInSeconden()==3600);
		assertTrue(second.getLengteInSeconden()==(3600 * 25));
	}
	@Test
	public void testOverlap(){
		assertTrue(third.overlapt(first));
		assertTrue(first.overlapt(third));
		assertFalse(second.overlapt(first));
		assertTrue(second.overlapt(third));
		assertTrue(third.overlapt(second));
	}
	
	@Test
	public void testCompareTo(){
		assertTrue(first.compareTo(third)==-1);
		assertTrue(fourth.compareTo(first)==-2);
		assertFalse(third.compareTo(first)==2);
		assertTrue(first.compareTo(first)==0);
	}
}
