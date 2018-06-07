import Services.Impl.CacheImpl;

public class CacheServiceTest {

	public static void main(String[] args) {
		
//		CacheImpl<Integer> intCache = CacheImpl.<Integer>getInstance();
//		CacheImpl<String> stringCache = CacheImpl.<String>getInstance();
		CacheImpl otherCache = CacheImpl.getInstance();
		CacheImpl otherCache2 = CacheImpl.getInstance();

		System.out.println(otherCache);
		System.out.println(otherCache2);
		
//		intCache.put("one", 1);
//		System.out.println(intCache.get("one"));
//		intCache.remove("one");
//		System.out.println(intCache.get("one"));
//		System.out.println("-----");
//		stringCache.put("one", "1");
//		System.out.println(stringCache.get("one"));
//		intCache.remove("one");
//		System.out.println(stringCache.get("one"));
//		
//		
//		otherCache.put("one", 1);
//		otherCache.put("two", "STRING");
//		System.out.println(otherCache.get("one").getClass().getName());
//		System.out.println(otherCache.get("two").getClass().getName());
		
		otherCache.put("one", new Object());
		
		System.out.println(otherCache.get("one"));
		
		try {
			Thread.currentThread().sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(otherCache.get("one"));
		
	}

}
