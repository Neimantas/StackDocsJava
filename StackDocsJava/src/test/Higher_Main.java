import Models.DAL.TopicsDAL;
import Models.DTO.ReadTableDTO;
import Services.Impl.CRUD;
import Services.Impl.HigherServiceImpl;

public class Higher_Main {
	
	public static void main(String[] args) {
		
		HigherServiceImpl hs = new HigherServiceImpl();
//		System.out.println(hs.getTopicsByLanguageId(5).isSuccess());
//		System.out.print(hs.getTopicInfoByTopicId(66).getTopics().get(0).title);
//		System.out.println(hs.getAllTopics().getTopics().size());
//		System.out.println(hs.getExampleByExampleId(5).getExamples().get(0).title);
//		System.out.println(hs.getAllLanguages().getLanguageTag().size());
//		for(TopicsDAL t: hs.getTopicsByLanguageId(5).getTopics()) {
//			System.out.println(t.introductionHtml);
//		}
//		CRUD cd = new CRUD();
//		ReadTableDTO rdto = cd.read("Topics WHERE LanguageId = 5 ");
//		System.out.println(rdto.getReadResultSet());
		
//		hs.getAllTopics();
//		try {
//			Thread.currentThread().sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("-----");
//		System.out.println(hs.getAllTopics().getTopics().size());
//		try {
//			Thread.currentThread().sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("-------");
//		System.out.println(hs.getAllTopics().getTopics().size());
		
		hs.getAllLanguages();
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----");
		System.out.println(hs.getAllLanguages().getLanguageTag().size());
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-------");
		System.out.println(hs.getAllLanguages().getLanguageTag().size());
	}

}
