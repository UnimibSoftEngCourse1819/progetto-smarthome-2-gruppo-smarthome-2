package middleware;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.IDescriptor;

public class RestClient {

	
	private static final RestClient INSTANCE = new RestClient();
	
	private Client client; 
	private WebTarget myResource;
	private UriBuilder uBuild;
	
	
	public static RestClient getINSTANCE() {
				return INSTANCE;		
	}
	
	//Il costruttore si comporta come un singleton..
	private RestClient(){
			this.client = ClientBuilder.newClient();
			this.uBuild = new UriBuilder();
	}
	
	public File get() throws MiddlewareException{
		this.uBuild.clear();
		return this.makeTheCall(uBuild.add("/devices").getStringUri());
	}
	
	
	public File get(IDescriptor desc) throws MiddlewareException{
		this.uBuild.clear();
		this.uBuild.add("/devices")
			.add("/")
			.add(desc.getId())
			.add("/")
			.add("functions");
		System.out.println(this.uBuild.getStringUri());
		return makeTheCall(this.uBuild.getStringUri());
	}

	private File makeTheCall(String uri) throws MiddlewareException {
			this.myResource = client.target(uri); 
			Invocation.Builder invocationBuilder =  this.myResource.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get(); 
			int stato = response.getStatus();
			if (stato >= 300 )  // eventuali controlli sullo stato di ritorno...
					{
						throw new MiddlewareException("si è riscontrato un problema di chiamata");
					}
			return response.readEntity(File.class);
	}

}
