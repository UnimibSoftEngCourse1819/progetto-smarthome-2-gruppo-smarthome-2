package middleware;

import java.io.IOException;

public interface ICache {
	
	<T> void addToCache(T obj);
	
	<T> void isInCache(T obj) throws AlreadyInCacheException, IOException;
}
