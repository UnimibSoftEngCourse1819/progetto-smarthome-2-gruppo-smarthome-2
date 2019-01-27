package middleware;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import adapters.DescriptorAdapter;
import adapters.FunctionAdapter;
import domain.ICommand;
import domain.IDescriptor;
import domain.IFunction;
import middleware.converters.IConverter;
import middleware.converters.Converter;



public class MiddlewareFacade implements IMiddlewareFacade {
	
	ICache cache = new FileCache();
	RestClient client = RestClient.getINSTANCE();
	
	@Override
	public Collection<IDescriptor> getDevices() throws MiddlewareException {
		File jsonFile = client.get();
		JSONArray jsoArray = new JSONArray();
		IConverter converter = new Converter();
		this.cache.isInCache(jsonFile); // per evitare di creare dei descrittori in più..
		jsoArray = converter.convert(jsonFile);
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
		JSONArray jsonArr = converter.convert(jsonFile);
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
	
}
