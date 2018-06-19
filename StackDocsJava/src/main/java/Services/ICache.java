package Services;

public interface ICache<T> {
	
	void put(String key, T data);
	T get(String key);
	void remove(String key);
//	ICache getCache();
}
