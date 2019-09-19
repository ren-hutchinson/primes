package com.rensoft.demo.primes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Test;

public class AppTest
{
	private App app;
	
	@Test
    public void testErrorState()
    {
		ByteArrayInputStream in = new ByteArrayInputStream("1\nlpll/ewer\npoiy\npoiy".getBytes());
		System.setIn(in);
		App app = new App(3);
		app.askForValues();
		assertTrue(app.isInError());
    }
	
	@Test
    public void testDecentInput()
    {
		ByteArrayInputStream in = new ByteArrayInputStream("1\n213\n".getBytes());
		System.setIn(in);
		App app = new App(3);
		app.askForValues();
		assertFalse(app.isInError());
    }
	
	@Test 
	public void testBasicProps() {
		final int TEST_ERRORS = 3;
		final int TEST_START = 1;
		final int TEST_END = 200;
		App app = new App(3);
		assertFalse(app.isInError());
		assertEquals(app.getMaxErrors(), TEST_ERRORS);
		app.setStartingValue(TEST_START);
		assertEquals(TEST_START, app.getStartingValue());
		app.setEndingValue(TEST_END);
		assertEquals(TEST_END, app.getEndingValue());
	}
	
	@Test
	public void testList() {
		App app = new App();
		app.setStartingValue(1);
		app.setEndingValue(100);
		List<Integer> primes = app.generatePrimes();
		assertEquals(primes.size(), 25);
	}
	
	@Test 
	public void testNoArgsNoErrorsMain() {
		ByteArrayInputStream in = new ByteArrayInputStream("1\n100\n".getBytes());
		System.setIn(in);
		App.main(new String[] {});
		assertNotNull(App.primes);
	}
	
	@Test 
	public void testArgssNoErrorsMain() {
		ByteArrayInputStream in = new ByteArrayInputStream("1\n100\n".getBytes());
		System.setIn(in);
		App.main(new String[] {"12"});
		assertNotNull(App.primes);
	}
	
	@Test 
	public void testArgssErrorsMain() {
		ByteArrayInputStream in = new ByteArrayInputStream("lp\n100lp\n".getBytes());
		System.setIn(in);
		App.main(new String[] {"1"});
		assertNull(App.primes);
	}
	
	@Test 
	public void testNoArgssErrorsMain() {
		ByteArrayInputStream in = new ByteArrayInputStream("lp\n100lp\niop\nkj\n".getBytes());
		System.setIn(in);
		App.main(new String[] {});
		assertNull(App.primes);
	}
	
	@Test 
	public void testErrorArgsMain() {
		ByteArrayInputStream in = new ByteArrayInputStream("1\n100\n".getBytes());
		System.setIn(in);
		App.main(new String[] {"cat"});
		assertNotNull(App.primes);
	}
}
