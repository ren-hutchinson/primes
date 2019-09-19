package com.rensoft.demo.primes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {
	
	private PrimeNumberGenerator gen;
	
	@Before	
	public void init() {
		gen = new Generator();
	}
	
	@Test
	public void testLessThan2() {
		assertFalse(gen.isPrime(1));
		assertFalse(gen.isPrime(0));
		assertFalse(gen.isPrime(-1));
		assertFalse(gen.isPrime(-2));
		assertFalse(gen.isPrime(-3));
	}
	
	@Test
	public void testFirstTen() {
		assertTrue(gen.isPrime(2));
		assertTrue(gen.isPrime(3));
		assertFalse(gen.isPrime(4));
		assertTrue(gen.isPrime(5));
		assertFalse(gen.isPrime(6));
		assertTrue(gen.isPrime(7));
		assertFalse(gen.isPrime(8));
		assertFalse(gen.isPrime(9));
		assertFalse(gen.isPrime(10));
	}
	
	@Test
	public void testAroundMax() {
		assertTrue(gen.isPrime(Integer.MAX_VALUE));
		assertFalse(gen.isPrime(Integer.MAX_VALUE - 1));
	}
	
	@Test
	public void testZeroBounds() {
		List<Integer> list = gen.generate(0, 0);
		assertEquals(list.size(), 0);
		list = gen.generate(1, 1);
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void testInvalidRange() {
		List<Integer> list = gen.generate(-127, 3);
		assertEquals(list.size(), 2);
		//other way around
		list = gen.generate(3, -127);
		assertEquals(list.size(), 2);
	}
	
	@Test
	public void testIncludeBounds() {
		List<Integer> list = gen.generate(5, 20);
		//2, 3, 5, 7, 11, 13, 17, 19
		assertEquals(list.size(), 6);
		list = gen.generate(20, 0);
		assertEquals(list.size(), 8);
	}
	
	@Test
	public void testOuterBound() {
		List<Integer> list = gen.generate(Integer.MAX_VALUE, Integer.MAX_VALUE - 10);
		assertEquals(list.size(), 1);
	}
	
	@Test
	//Values taked from here: https://primes.utm.edu/howmany.html
	public void testknownTotalsPrimes() {
		List<Integer> list = gen.generate(0, 1000000);
		list = gen.generate(0, 10000000);
		assertEquals(list.size(), 664579);
		list = gen.generate(0, 100000000);
		assertEquals(list.size(), 5761455);
		list = gen.generate(0, 1000000000);
		assertEquals(list.size(), 50847534);
	}
	
	@Test
	public void testDesiredRange() {
		 List<Integer> list = gen.generate(7900, 7920);
		 assertEquals(list.size(), 3);
		 assertEquals(list.get(0).intValue(), 7901);
		 assertEquals(list.get(1).intValue(), 7907);
		 assertEquals(list.get(2).intValue(), 7919);
	}
	
	@Test 
	public void testBackAndForth() {
		List<Integer> a = gen.generate(1, 100);
		List<Integer> b = gen.generate(100, 1);
		assertEquals(a, b);
	}
}
