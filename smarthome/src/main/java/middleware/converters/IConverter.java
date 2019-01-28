package middleware.converters;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import middleware.MiddlewareException;



public interface IConverter {
	
	JSONArray convertToJsonArray(JSONObject obj) throws MiddlewareException;

}
