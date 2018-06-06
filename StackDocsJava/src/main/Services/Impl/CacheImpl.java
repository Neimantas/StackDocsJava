package Services.Impl;

import java.util.HashMap;
import java.util.Map;

import Services.ICache;

public class CacheImpl<T> implements ICache<T> {
	
	private Map<String, T> objects = new HashMap<>();
	private static CacheImpl instance = null;
	
	//Cannot instantiate Cache usual way. Use getInstance method instead
	private CacheImpl() {}
	
	@Override
	public void put(String key, T data) {
		
		objects.put(key, data);
	}

	@Override
	public T get(String key) {
		
		return objects.get(key);
	}

	@Override
	public void remove(String key) {
		
		objects.remove(key);
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> CacheImpl<T> getInstance() {
		
		if(instance == null) {
			instance = new CacheImpl<T>();
			return instance;
		}
		return instance;
	}


}
