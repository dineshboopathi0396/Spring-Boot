package com.dinesh.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class SampleMathTest {

	SampleMath sample = new SampleMath();

//	@BeforeClass
//	public static void beforeClass() {
//		System.out.println("Before Class");
//	}

	@Before
	public void before() {
		System.out.println("Before");
	}
//
//	@After
//	public void after() {
//		System.out.println("After");
//	}
//
//	@AfterClass
//	public static void afterClass() {
//		System.out.println("After Class");
//	}

	@Test
	void sumfor3Numbers() {
//		fail("Not yet implemented");
		System.out.println("Test1");
		int result = sample.sum(new int[] { 1, 2, 3 });
		assertEquals(6, result);
	}

	@Test
	void sumfor1Numbers() {
		System.out.println("Test2");
		int result = sample.sum(new int[] { 3 });
		assertEquals(3, result);
	}

}
