package middleware;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

public class FileCache implements ICache {
	
	Collection<File> dynamicCache;
	
	public FileCache(){
		//TODO Potrebbe non essere la scelta migliore
		this.dynamicCache = new ArrayList<File>();
	}

	@Override
	public <File> void isInCache(File obj) throws AlreadyInCacheException, IOException {
		//if(this.dynamicCache.contains(obj))
		for(java.io.File f : this.dynamicCache)
			if(FileUtils.contentEquals(f, (java.io.File) obj))
				throw new AlreadyInCacheException();
		System.out.println("Not In Cache!");
		this.addToCache(obj);
	}

	@Override
	public <File> void addToCache(File obj) {
		this.dynamicCache.add((java.io.File) obj);
		
	}

}
