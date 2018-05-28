

import java.util.ArrayList;
import java.util.List;

import Models.DAL.ExamplesDAL;
import Models.DAL.TopicsDAL;
import Services.IHigherService;
import Services.Impl.CRUD;
import Services.Impl.HigherServiceImpl;

public class CRUD_Main {

	public static void main(String[] args) {
		
		CRUD crud = new CRUD();
			System.out.println(crud.read("LanguageTags"));

		IHigherService serv = new HigherServiceImpl();
		
//		System.out.println(serv.readLanguageTag("Bla").size());
//		System.out.println(serv.readTopics("").size());
//		System.out.println(serv.readExamples("").size());
//		for(int i = 0; i<10; i++) {
//			TopicsDAL t = (TopicsDAL) serv.getTopicsByLanguageId(5).get(i);
//			System.out.println(t.title);
//		}
		for(int i = 0; i<10; i++) {
			ExamplesDAL t = (ExamplesDAL) serv.getExamplesByTopicId(1).get(i);
			System.out.println(t.title);
		}
		
	}

}
