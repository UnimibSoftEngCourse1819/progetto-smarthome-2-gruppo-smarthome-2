package middleware;

import java.io.File;


public interface ICache {
	
	public void addToCache(File obj);
	
	public void isInCache(File obj) throws MiddlewareException;
}
