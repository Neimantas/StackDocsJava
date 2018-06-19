package Services.Impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Services.ICache;
import javax.inject.Singleton;

@Singleton
public class CacheImpl<T> implements ICache<T> {
	
	private Map<String, T> objects = Collections.synchronizedMap(new HashMap<>());
	private Map<String, Long> expire = Collections.synchronizedMap(new HashMap<>());
	private long expireTimeDefault = 100; //100 seconds;
	
	
	private static CacheImpl instance = null;
	
	public CacheImpl() {
		instance = this;
	}
	
	@Override
	public void put(String key, T data) {
		
		objects.put(key, data);
		expire.put(key, System.currentTimeMillis() + expireTimeDefault * 1000);
		
	}
	
	public void put(String key, T data, long timeExpires) {
		
		objects.put(key, data);
		expire.put(key, System.currentTimeMillis() + timeExpires * 1000);
		
	}

	@Override
	public T get(String key) {
		
		if(!objects.containsKey(key))
			return null;
		
		if (System.currentTimeMillis() > expire.get(key)) {
			objects.remove(key);
			expire.remove(key);
			return null;
		}
		return objects.get(key);
	}

	@Override
	public void remove(String key) {
		
		objects.remove(key);
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> CacheImpl<T> getInstance() {
//		
//		if(instance == null) {
//			instance = new CacheImpl<T>();
//			return instance;
//		}
		return instance;
	}


}
