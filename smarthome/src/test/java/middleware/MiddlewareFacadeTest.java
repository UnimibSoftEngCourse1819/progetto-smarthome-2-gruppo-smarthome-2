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
import domain.Property;
import exceptions.MiddlewareException;
import middleware.converters.Converter;

public class MiddlewareFacadeTest {
	private Converter converter;
	
	public MiddlewareFacadeTest() {
		this.converter = new Converter();
	}
	
	private void addArrayParameter(Property prop, JSONObject resource) throws MiddlewareException {
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

	private void addObjectParameter(Property prop, JSONObject resource) throws MiddlewareException {
		JSONObject obj;
		obj = converter.convertToJsonObject(resource);
		prop.clear();
		for (Object key : obj.keySet()) 
			prop.addParameter(key, obj.get(key));
	}

	private Collection<IFunction> getFunctions(JSONArray functs){
		List<IFunction> adapters = new ArrayList<>();
		for(Object obj : functs)
			 adapters.add((new FunctionAdapter((JSONObject) obj)));
		return adapters;
		}
	
	private Collection<IDescriptor> getDescriptorsAdapters(JSONArray jarr) {
		List<IDescriptor> adapters = new ArrayList<IDescriptor>();
		for(Object job : jarr) 
			adapters.add(new DescriptorAdapter((JSONObject) job));
		return adapters;
	}

	// persistence and tests
	public Collection<IFunction> getADeviceFunctionsTest(IDescriptor desc, File json) throws MiddlewareException{
		JSONObject resource = converter.parseJSON(json);
		JSONArray jsonArr = converter.convertToJsonArray(resource);
		return this.getFunctions(jsonArr);
			}
			
			// persistence and tests
	public Collection<IDescriptor> getDevicesTest(File json) throws MiddlewareException {
		JSONObject resource = converter.parseJSON(json);
		JSONArray jsoArray = converter.convertToJsonArray(resource);
		return this.getDescriptorsAdapters(jsoArray);
			}
			
	public Property getPropertyTest(Property prop, File json) throws MiddlewareException {
		JSONObject resource = converter.parseJSON(json);
		if(converter.isJSONObject(resource))
			 addObjectParameter(prop, resource);
		else
			 addArrayParameter(prop, resource);
		return prop;
		}
}
