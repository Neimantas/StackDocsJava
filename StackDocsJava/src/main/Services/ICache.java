package Services;

public interface ICache {
	void put(String key, Object data);
	Object get(String key);
	void remove(String key);
}
