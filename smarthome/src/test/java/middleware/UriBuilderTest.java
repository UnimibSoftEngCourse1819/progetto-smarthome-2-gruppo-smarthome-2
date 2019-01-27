package middleware;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UriBuilderTest {

	UriBuilder uriBuilder;
	
@Test // costruttore uriBuilder

	public void testUriBuilder() 
	{
	    this.uriBuilder = new UriBuilder();
	    assertEquals("http://localhost:8080/api", this.uriBuilder.getStringUri());
	}

@Test // add
	public void testAdd() {
		this.uriBuilder = new UriBuilder();
		String coda = "/";
		String coda1 = "esempio";
		String coda2 = "/esempio";
		this.uriBuilder.add(coda);
		this.uriBuilder.add(coda1);
		this.uriBuilder.add(coda2);
		assertEquals("http://localhost:8080/api/esempio/esempio", this.uriBuilder.getStringUri());

	}

@Test // clear
public void testClear() {
		this.uriBuilder = new UriBuilder();
		String coda = "/";
		String coda1 = "esempio";
		String coda2 = "/esempio";
		this.uriBuilder.add(coda);
		this.uriBuilder.add(coda1);
		this.uriBuilder.add(coda2);
		this.uriBuilder.clear();
		assertEquals("http://localhost:8080/api", this.uriBuilder.getStringUri());
}

}
