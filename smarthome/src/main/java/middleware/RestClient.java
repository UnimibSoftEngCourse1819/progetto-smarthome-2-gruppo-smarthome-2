package middleware;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import domain.IDescriptor;
import domain.Operation;
import domain.Property;
import exceptions.MiddlewareException;

public class RestClient {

	private static final RestClient INSTANCE = new RestClient();
	
	private Client client; 
	private WebTarget myResource;
	private UriBuilder uBuild;
	
	
	public static RestClient getINSTANCE() {
				return INSTANCE;		
	}
	
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
		return makeTheCall(this.uBuild.getStringUri());
	}

	private File makeTheCall(String uri) throws MiddlewareException {
			this.myResource = client.target(uri); 
			Invocation.Builder invocationBuilder =  this.myResource.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get(); 
			int stato = response.getStatus();
			if (stato >= 300 )  
					{
						throw new MiddlewareException("si è riscontrato un problema di chiamata");
					}
			return response.readEntity(File.class);
	}

	public File post(Property prop) throws MiddlewareException {
		this.uBuild.clear();
		this.uBuild.add("/functions/");
		this.uBuild.add(prop.getFunctionId());
		JSONObject body = this.constructGetPropBody(prop);
		return this.makeTheCall(this.uBuild.getStringUri(), body);
	}
	
	
	private JSONObject constructGetPropBody(Property prop) {
		JSONObject body = new JSONObject();
		String app = prop.getName().toString().substring(0, 1).toUpperCase() + prop.getName().toString().substring(1);
		body.put("operation", "get" + app);
		return body;
	}

	public File makeTheCall(String uri, JSONObject body) throws MiddlewareException{
		this.myResource = client.target(uri); 
		Invocation.Builder invocationBuilder =  this.myResource.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.post(Entity.entity(body, MediaType.APPLICATION_JSON));
		
		if (response.getStatus()>= 300 )
			throw new MiddlewareException("si è riscontrato un problema di chiamata");
		
		return response.readEntity(File.class);

	}

	public File post(Operation operation) throws MiddlewareException {
		this.uBuild.clear();
		this.uBuild.add("/functions/");
		this.uBuild.add(operation.getFunctionId());
		JSONObject body = this.constructOperationBody(operation);
		return this.makeTheCall(this.uBuild.getStringUri(), body);
	}

	private JSONObject constructOperationBody(Operation operation) {
		JSONObject body = new JSONObject();
		body.put("operation", (String) operation.getName());
		return body;
	}
		
}
