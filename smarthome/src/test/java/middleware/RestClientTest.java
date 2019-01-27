package middleware;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class RestClientTest {

	RestClient restClient = RestClient.getINSTANCE();
	
@Test //costruttore RestClient
public void testRestClient() {
	assertNotNull(restClient);
}


}
