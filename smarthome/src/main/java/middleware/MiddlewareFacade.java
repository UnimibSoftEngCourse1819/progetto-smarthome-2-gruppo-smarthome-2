package middleware;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import adapters.DescriptorAdapter;
import adapters.FunctionAdapter;

import domain.IDescriptor;
import domain.IFunction;
import domain.Operation;
import domain.Property;
import middleware.converters.IConverter;
import middleware.converters.Parser;
import middleware.converters.Converter;



public class MiddlewareFacade implements IMiddlewareFacade {
	//TODO CONVERTER ATTRIBUTO
	ICache cache = new FileCache();
	RestClient client = RestClient.getINSTANCE();
	
	@Override
	public Collection<IDescriptor> getDevices() throws MiddlewareException {
		File jsonFile = client.get();
		JSONArray jsoArray = new JSONArray();
		IConverter converter = new Converter();
		this.cache.isInCache(jsonFile); // per evitare di creare dei descrittori in più..
		jsoArray = converter.convertToJsonArray(jsonFile);
		return this.getDescriptors(jsoArray);
	}
	
	private Collection<IDescriptor> getDescriptors(JSONArray jarr) {
		List<IDescriptor> adapters = new ArrayList<IDescriptor>();
		for(Object job : jarr) // per ciascun dispositivo 
			adapters.add(new DescriptorAdapter((JSONObject) job));
		return adapters;
	}
		
	public Collection<IFunction> getADeviceFunctions(IDescriptor desc) throws MiddlewareException{
		File jsonFile = client.get(desc);
		IConverter converter = new Converter();
		JSONArray jsonArr = converter.convertToJsonArray(jsonFile);
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
		JSONObject obj = conv.convertToJsonObject(jsonFile);
		prop.clear();
		for (Object key : obj.keySet()) {
			prop.addParameter(key, obj.get(key));
		}
		return prop;
	}
	
	public void executeOperation(Operation operation) throws MiddlewareException {
		File jsonFile = this.client.post(operation);
	}
	
}
