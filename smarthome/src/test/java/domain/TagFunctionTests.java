package domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TagFunctionTests {

	
	@Test
	public void constructor() {
		TagFunction tagFunction = new TagFunction();
		assertTrue(tagFunction.getTagValue().equals("dal.function."));
	}

	@Test
	public void constructorParameter() {
		TagFunction tagFunction = new TagFunction("example");
		assertTrue(tagFunction.getTagValue().equals("dal.function.example"));
	}
}
