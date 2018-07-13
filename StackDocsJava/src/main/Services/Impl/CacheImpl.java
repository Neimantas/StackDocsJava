package Services.Impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Services.ICache;
import javax.inject.Singleton;

import Models.Const.Settings;

@Singleton
public class CacheImpl implements ICache {

	private Map<String, Object> _objects = Collections.synchronizedMap(new HashMap<>());
	private Map<String, Long> _expire = Collections.synchronizedMap(new HashMap<>());

	@Override
	public void put(String key, Object data) {

		_objects.put(key, data);
		_expire.put(key, System.currentTimeMillis() + Settings.CACHE_DATA_DEFAULT_EXPIRE_TIME * 1000);

	}
	
											//time in seconds
	public void put(String key, Object data, long timeExpires) {

		_objects.put(key, data);
		_expire.put(key, System.currentTimeMillis() + timeExpires * 1000);

	}

	@Override
	public Object get(String key) {

		if (!_objects.containsKey(key))
			return null;

		if (System.currentTimeMillis() > _expire.get(key)) {
			_objects.remove(key);
			_expire.remove(key);
			return null;
		}
		return _objects.get(key);
	}

	@Override
	public void remove(String key) {

		_objects.remove(key);

	}

}
