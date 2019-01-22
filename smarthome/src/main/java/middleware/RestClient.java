package middleware;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import domain.IDescriptor;

public class RestClient {

	Client client; 
	WebTarget myResource;
	UriBuilder uBuild;
	
	
	public RestClient(){
		//Il costruttore si comporta come un singleton
		if(this.client == null)
			this.client = ClientBuilder.newClient();
		if(this.uBuild == null)
			this.uBuild = new UriBuilder();
	}
	
	public File get(){
		this.uBuild.clear();
		return this.makeTheCall(uBuild.add("/devices").getStringUri());
	}
	
	//Inutile passare LowObject ha piu senso permettere allo stesso
	//Metodo di capire a seconda del tipo di oggetto passato quale
	//Chiamata Rest Deve fare
	public File get(IDescriptor desc){
		this.uBuild.clear();
		this.uBuild.add("/devices")
			.add("/")
			.add(desc.getId())
			.add("/")
			.add("functions");
		System.out.println(this.uBuild.getStringUri());
		return makeTheCall(this.uBuild.getStringUri());
	}



	public File makeTheCall(String uri) {
		this.myResource = client.target(uri); 
		return this.myResource.request(MediaType.APPLICATION_JSON).get(File.class);
	}
	

}
