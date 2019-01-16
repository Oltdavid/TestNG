package testpackage;

import org.testng.Assert;
import org.testng.annotations.Test;
import appcode.SomeClassToTest;

public class TestNG_TestAnnotation {

	@Test
	public void testMethod1() {
		SomeClassToTest obj = new SomeClassToTest();
		int result = obj.sumNumbers(1, 2);
		System.out.println("eredmény: " + result);
		Assert.assertEquals(result, 3);
		System.out.println("Running Test -> testMethod1");
	}

	@Test
	public void testMethod2() {
		System.out.println("Running Test -> testMethod2");
		String exceptedString = "Hello World";
		SomeClassToTest obj = new SomeClassToTest();
		String result = obj.addStrings("Hello", "World");
		Assert.assertEquals(result, exceptedString);

	}

	@Test
	public void testMethod3() {
		System.out.println("Running Test -> testArrays");
		int[] exceptedArray = {1, 1, 3};
		SomeClassToTest obj = new SomeClassToTest();
		int[] result = obj.getArray();
		Assert.assertEquals(result, exceptedArray);
	}
}