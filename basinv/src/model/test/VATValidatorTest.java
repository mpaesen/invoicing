package model.test;

import static org.junit.Assert.*;

import model.VATValidator;

import org.junit.Before;
import org.junit.Test;

public class VATValidatorTest {
	private String digit1, digit2;

	@Before
	public void setUp() throws Exception {
		digit1= "10";
		digit2= "20";
	}

	@Test
	public void testMultiplyAdd() {
		assertTrue(VATValidator.multiplyAdd(digit1, digit2)==2);
	}

	@Test
	public void testBEcheck9() {
		assertTrue(VATValidator.BEcheck("886178043"));

	}
	@Test
	public void testBEcheck10() {
		assertTrue(VATValidator.BEcheck("0886178043"));

	}
	@Test
	public void testBEcheck12() {
		assertTrue(VATValidator.BEcheck("BE0886178043"));

	}

	@Test
	public void testNLcheck() {
		// fail("Not yet implemented");
	}

}
