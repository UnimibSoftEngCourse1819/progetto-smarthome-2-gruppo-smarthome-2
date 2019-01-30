package domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TagTests {

	
	@Test
	public void tagConstructor() {
		Tag tag = new Tag();
		 assertTrue(tag.getRoot()==null);
		 assertTrue(tag.getParameter()==null);
	}
	
	@Test
	public void tagConstructorRoot() {
		Tag tag = new Tag("example");
		 assertTrue(tag.getRoot().equals("example"));
		 assertTrue(tag.getParameter()==null);
	}
	
	@Test
	public void getTagValue() {
		Tag tag = new Tag("example");
		tag.setParameter("parameter");
		assertTrue(tag.getTagValue().equals("exampleparameter"));
	}
	
	@Test
	public void setRootParameter() {
		Tag tag = new Tag();
		tag.setRoot("example");
		tag.setParameter("parameter");
		assertTrue(tag.getTagValue().equals("exampleparameter"));
	}
}
