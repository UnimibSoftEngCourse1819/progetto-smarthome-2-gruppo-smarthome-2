package middleware.converters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.json.simple.JSONArray;

import org.json.simple.parser.ParseException;



public interface IConverter {
	
	JSONArray convert(File f) throws FileNotFoundException, IOException, ParseException;

}
