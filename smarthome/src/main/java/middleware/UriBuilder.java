package middleware;

public class UriBuilder {
	
	private static final String BASEURI = "http://localhost:8080/api";
	
	private String uri;
	
	public UriBuilder(){
		this.uri = "";
	}
	
	public UriBuilder add(Object resource){
		this.uri += (resource.toString());
		return this;
	}
	
	public String getStringUri(){
		return this.BASEURI + this.uri;
	}
	
	public void clear(){
		this.uri = "";
	}
	
}
