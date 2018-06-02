import Services.Impl.HigherServiceImpl;

public class Higher_Main {
	
	public static void main(String[] args) {
		
		HigherServiceImpl hs = new HigherServiceImpl();
		System.out.println(hs.getTopicsByLanguageId(5).isSuccess());
	}

}
