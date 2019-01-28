package middleware.converters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import middleware.MiddlewareException;



public interface IConverter {
	
	JSONArray convertToJsonArray(JSONObject obj) throws MiddlewareException;

}
