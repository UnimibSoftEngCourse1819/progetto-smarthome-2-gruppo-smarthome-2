package middleware;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class FileCache implements ICache {
	
	Collection<File> dynamicCache;
	
	public FileCache(){
		//TODO Potrebbe non essere la scelta migliore
		this.dynamicCache = new ArrayList<File>();
	}

	@Override
	public void isInCache(File obj) throws MiddlewareException{
		for(java.io.File f : this.dynamicCache)
			try {
				if(FileUtils.contentEquals(f, (java.io.File) obj))
					throw new MiddlewareException(new AlreadyInCacheException());
			} catch (IOException e) {
				throw new MiddlewareException(e);
			}
		this.addToCache(obj);
	}

	@Override
	public void addToCache(File obj) {
		this.dynamicCache.add((java.io.File) obj);
	}

}
