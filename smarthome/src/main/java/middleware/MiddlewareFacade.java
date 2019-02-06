package middleware;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import adapters.DescriptorAdapter;
import adapters.FunctionAdapter;
import domain.DeviceDescriptor;
import domain.ICommand;
import domain.IDescriptor;
import domain.IFunction;
import domain.Operation;
import domain.Property;
import domain.TagFunction;
import exceptions.MiddlewareException;
import middleware.converters.Converter;
import persistance.PersistanceController;



public class MiddlewareFacade implements IMiddlewareFacade {
	private ICache cache;
	private RestClient client; 
	private Converter converter;
	private PersistanceController db;
	
	
	public MiddlewareFacade() throws MiddlewareException{
		this.cache = new FileCache();
		this.converter = new Converter();
		this.client = RestClient.getINSTANCE();
		this.db = new PersistanceController();	
	}
	
	
	@Override
	public Collection<IDescriptor> getDevices() throws MiddlewareException {
		File jsonFile = client.get();
		this.cache.isInCache(jsonFile); 
		JSONObject resource = converter.parseJSON(jsonFile);
		JSONArray jsoArray = converter.convertToJsonArray(resource);
		return this.getDescriptorsAdapters(jsoArray);
	}
	
	
	
	public Collection<IDescriptor> getSavedDevices() throws MiddlewareException{
		System.out.println(converter.convertToJsonArray(this.db.getJsonObjectFile()));
		return getDescriptorsAdapters(converter.convertToJsonArray(this.db.getJsonObjectFile()));
	}
	
	private Collection<IDescriptor> getDescriptorsAdapters(JSONArray jarr) {
		List<IDescriptor> adapters = new ArrayList<IDescriptor>();
		for(Object job : jarr) 
			adapters.add(new DescriptorAdapter((JSONObject) job));
		return adapters;
	}
		
	public Collection<IFunction> getADeviceFunctions(IDescriptor desc) throws MiddlewareException{
		File jsonFile = client.get(desc);
		JSONObject resource = converter.parseJSON(jsonFile);
		JSONArray jsonArr = converter.convertToJsonArray(resource);
		return this.getFunctions(jsonArr);
	}
	
	
	private Collection<IFunction> getFunctions(JSONArray functs){
		List<IFunction> adapters = new ArrayList<>();
		for(Object obj : functs)
			 adapters.add((new FunctionAdapter((JSONObject) obj)));
		return adapters;
		}

	
	@Override
	public Property getProperty(Property prop) throws MiddlewareException {
		File jsonFile = this.client.post(prop);
		JSONObject resource = converter.parseJSON(jsonFile);
		if(converter.isJSONObject(resource))
			 addObjectParameter(prop, resource);
		else
			addArrayParameter(prop, resource);
		return prop;
	}
	

	private void addArrayParameter(Property prop, JSONObject resource) {
		JSONArray arr;
		arr = converter.convertToJsonArray(resource);
		prop.clear();
		for (Object ogg : arr) {
			if(ogg != null){
				JSONObject item = ((JSONObject) ogg);
				for(Object key : item.keySet())
					prop.addParameter(key, item.get(key));
			}
		}
	}

	private void addObjectParameter(Property prop, JSONObject resource)  {
		JSONObject obj;
		obj = converter.convertToJsonObject(resource);
		prop.clear();
		for (Object key : obj.keySet()) 
			prop.addParameter(key, obj.get(key));
	}
	
	public void executeOperation(Operation operation) throws MiddlewareException {
		File jsonFile = this.client.post(operation);
	}

	@Override
	public Collection<Property> updateProperties(IFunction state) throws MiddlewareException {
		List<Property> properties = new ArrayList<>();
		for(ICommand command : state.getCommands()) {
			if(command.getTag().equals(new TagFunction("property.name")))
				properties.add(this.getProperty((Property) command));
		}
		return properties;
	}


	@Override
	public void saveDevice(List<DeviceDescriptor> desc) throws MiddlewareException {
		this.db.saveToFile(desc);	
	}
	

}
