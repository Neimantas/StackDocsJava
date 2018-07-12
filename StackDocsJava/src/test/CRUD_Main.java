

import java.util.ArrayList;
import java.util.List;

import Models.Business.CrudUpdate;
import Models.DAL.ExamplesDAL;
import Models.DAL.LanguageTagsDAL;
import Models.DAL.TopicsDAL;
import Models.DTO.UpdateTableDTO;

import Services.IHigherService;
import Services.Impl.CRUD;
import Services.Impl.CacheImpl;
import Services.Impl.DataBaseImpl;
import Services.Impl.HigherServiceImpl;

public class CRUD_Main {

	public static void main(String[] args) {
		
		CRUD crud = new CRUD(new DataBaseImpl(), new CacheImpl());
//		System.out.println(crud.read(new ExamplesDAL()).isSuccess());
//		System.out.println(crud.read(new LanguageTagsDAL()).isSuccess());
//		System.out.println(crud.read(new TopicsDAL()).isSuccess());
//		System.out.println(crud.read(new String()).isSuccess());
//		


		
//####################################################	
//		UpdateTableDTO updDTO = new UpdateTableDTO();
//		CrudUpdate params = new CrudUpdate();
//
//		params.conditionColumName = "a";
//		
//		updDTO = crud.update(params);
//####################################################
		
//		ExamplesDAL dal = new ExamplesDAL();
//		dal.bodyHtml = "thi is body";
//		dal.exampleId = 9919191;
//		dal.title = "Test";
//		dal.topicId = 99199;
//
//		
//		
//		System.out.println(crud.create(dal).getMessage());
		
		
//		ExamplesDAL dal = new ExamplesDAL();
//		ExamplesDAL rdal =(ExamplesDAL)crud.read(dal).getReadResultSet().get(0);
//		System.out.println(rdal.title + " " + rdal.exampleId + " "  + rdal.bodyHtml);
//		
//		
//		TopicsDAL dal1 = new TopicsDAL();
//		TopicsDAL rdal1 =(TopicsDAL)crud.read(dal1).getReadResultSet().get(0);
//		System.out.println(rdal1.title + " " + rdal1.languageId + " "  + rdal1.syntaxHtml);
//		
//		
//		LanguageTagsDAL dal3 = new LanguageTagsDAL();
//		LanguageTagsDAL rdal2 =(LanguageTagsDAL)crud.read(dal3).getReadResultSet().get(0);
//		System.out.println(rdal2.title + " " + rdal2.languageId + " "  + rdal2.tag);
		TopicsDAL tdal = new TopicsDAL();
		System.out.println("Speed with for");
		crud.read(tdal);
		System.out.println("speed without for");
//		crud.read3(tdal);
		
		System.out.println("Speed with for cached");
		crud.read(tdal);
		System.out.println("speed without for");
//		crud.read3(tdal);
		
	}

}
