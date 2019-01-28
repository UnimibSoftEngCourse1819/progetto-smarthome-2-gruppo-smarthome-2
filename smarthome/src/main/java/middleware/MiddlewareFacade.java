package middleware;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import adapters.DescriptorAdapter;
import adapters.FunctionAdapter;
import domain.Function;
import domain.ICommand;
import domain.IDescriptor;
import domain.IFunction;
import domain.Operation;
import domain.Property;
import domain.TagFunction;
import middleware.converters.IConverter;
import middleware.converters.Parser;
import middleware.converters.Converter;



public class MiddlewareFacade implements IMiddlewareFacade {
	//TODO CONVERTER ATTRIBUTO
	private ICache cache;
	private RestClient client; 
	private Converter converter;
	
	
	public MiddlewareFacade() {
		this.cache = new FileCache();
		this.converter = new Converter();
		this.client = RestClient.getINSTANCE();
		
	}
	
	@Override
	public Collection<IDescriptor> getDevices() throws MiddlewareException {
		File jsonFile = client.get();
		JSONArray jsoArray = new JSONArray();
		this.cache.isInCache(jsonFile); // per evitare di creare dei descrittori in più..
		JSONObject resource = converter.parseJSON(jsonFile);
		jsoArray = converter.convertToJsonArray(resource);
		return this.getDescriptorsAdapters(jsoArray);
	}
	
	private Collection<IDescriptor> getDescriptorsAdapters(JSONArray jarr) {
		List<IDescriptor> adapters = new ArrayList<IDescriptor>();
		for(Object job : jarr) // per ciascun dispositivo 
			adapters.add(new DescriptorAdapter((JSONObject) job));
		return adapters;
	}
		
	public Collection<IFunction> getADeviceFunctions(IDescriptor desc) throws MiddlewareException{
		File jsonFile = client.get(desc);
		Converter converter = new Converter();
		JSONObject resource = converter.parseJSON(jsonFile);
		JSONArray jsonArr = converter.convertToJsonArray(resource);
		this.getFunctions(jsonArr);
		return this.getFunctions(jsonArr);
	}
	
	
	private Collection<IFunction> getFunctions(JSONArray functs){
		List<IFunction> adapters = new ArrayList<>();
		for(Object obj : functs){
			//IFunction function = new Function();
			 adapters.add((new FunctionAdapter((JSONObject) obj)));
		}
		return adapters;
		}

	@Override
	public Property getProperty(Property prop) throws MiddlewareException {
		File jsonFile = this.client.post(prop);
		Converter conv = new Converter();
		JSONObject resource = conv.parseJSON(jsonFile);
		JSONObject obj;
		JSONArray arr;
		if(conv.isJSONObject(resource)){
			 obj = conv.convertToJsonObject(resource);
			 prop.clear();
			for (Object key : obj.keySet()) 
				prop.addParameter(key, obj.get(key));
		}
		else{
			arr = conv.convertToJsonArray(resource);
			prop.clear();
			for (Object ogg : arr) {
				if(ogg != null){
					JSONObject item = ((JSONObject) ogg);
					for(Object key : item.keySet())
						prop.addParameter(key, item.get(key));
				}
			}
		}
		return prop;
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
	
}
