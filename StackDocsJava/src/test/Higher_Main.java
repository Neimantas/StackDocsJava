import Models.Example;
import Models.Language;
import Models.Topic;
import Models.DAL.TopicsDAL;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.UpdateTableDTO;
import Services.Impl.CRUD;
import Services.Impl.CacheImpl;
import Services.Impl.DataBaseImpl;
import Services.Impl.HigherServiceImpl;

public class Higher_Main {
	
	public static void main(String[] args) {
		
		HigherServiceImpl hs = new HigherServiceImpl(new CRUD(new DataBaseImpl(), new CacheImpl()));
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
		
//		hs.getAllLanguages();
//		try {
//			Thread.currentThread().sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("-----");
//		System.out.println(hs.getAllLanguages().getLanguageTag().size());
//		try {
//			Thread.currentThread().sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("-------");
//		System.out.println(hs.getAllLanguages().getLanguageTag().size());
		
//		System.out.println(hs.getLanguageTagByLanguageId(5).isSuccess());
//		System.out.println(hs.getLanguageTagByLanguageId(5).getMessage());
//		System.out.println(hs.getTopicsByLanguageId(5).getTopics().size());
//		System.out.println(hs.getExamplesByTopicId(5).getExamples().get(0).title);
//		System.out.println(hs.getAllLanguages().getLanguageTag().size());
//		System.out.println(hs.getAllTopics().getTopics().size());
//		System.out.println(hs.getTopicInfoByTopicId(6).getTopics().get(0).title);
//		System.out.println(hs.getExampleByExampleId(1).getExamples().get(0).title);
//		System.out.println(hs.getLanguageTagByLanguageId(5).getLanguageTag().get(0).title);
//####################################################################		
//		Topic test = new Topic();
//		
//		test.set_LanguageId(5);
//		test.set_IntroductionHtml("TEST TEST TEST");
//		test.set_ParametersHtml("TEST TEST TEST");
//		
//		Example example = new Example();
//		
//		example.bodyHtml= " TEST TEST TEST TEST ";
//		example.title = " TEST EXAMPLE";
//		example.topicId = 5555;
//		
//		Language language = new Language("SUPERDUPERLANGUAGE", -1);
//		
//		System.out.println(hs.create(language).getMessage());
//######################################################################		
//		UpdateTableDTO upd;
//		Topic topic = new Topic();
//		topic.set_TopicId(1);
//		topic.set_TopicTitle("TEST_as");
//		upd = hs.update(topic);
//		System.out.println(upd.isSuccess());
//		System.out.println(upd.getMessage());
		
//###########################################################
		System.out.println(hs.getAllTopics().getTopics().size());
		Topic topic = new Topic();
		topic.set_TopicId(2);
		DeleteTableDTO del = hs.delete(topic);
		System.out.println(del.getMessage());
		System.out.println(hs.getAllTopics().getTopics().size());
		
		System.out.println(hs.getAllLanguages().getLanguageTag().size());
		Language language = new Language();//("", 1);
		
		DeleteTableDTO del1 = hs.delete(language);
		System.out.println(del1.getMessage());
		System.out.println(hs.getAllLanguages().getLanguageTag().size());
		
		System.out.println(hs.getAllExamples().getExamples().size());
		Example example = new Example();
		example.exampleId = 1;
		DeleteTableDTO del2 = hs.delete(example);
		System.out.println(del2.getMessage());
		System.out.println(hs.getAllExamples().getExamples().size());
	}

}
