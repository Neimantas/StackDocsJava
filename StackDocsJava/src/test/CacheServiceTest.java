import Services.Impl.CacheImpl;

public class CacheServiceTest {

	public static void main(String[] args) {
		
		CacheImpl<Integer> intCache = CacheImpl.<Integer>getInstance();
		CacheImpl<String> stringCache = CacheImpl.<String>getInstance();
		CacheImpl otherCache = CacheImpl.getInstance();
		
		intCache.put("one", 1);
		System.out.println(intCache.get("one"));
		intCache.remove("one");
		System.out.println(intCache.get("one"));
		System.out.println("-----");
		stringCache.put("one", "1");
		System.out.println(stringCache.get("one"));
		intCache.remove("one");
		System.out.println(stringCache.get("one"));
		
		
		otherCache.put("one", 1);
		otherCache.put("two", "STRING");
		System.out.println(otherCache.get("one").getClass().getName());
		System.out.println(otherCache.get("two").getClass().getName());
		
		
		
		
	}

}
