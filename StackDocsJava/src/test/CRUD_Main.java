

import java.util.ArrayList;
import java.util.List;

import Models.CrudUpdate;
import Models.DAL.ExamplesDAL;
import Models.DAL.LanguageTagsDAL;
import Models.DAL.TopicsDAL;
import Models.DTO.UpdateTableDTO;

import Services.IHigherService;
import Services.Impl.CRUD;
import Services.Impl.HigherServiceImpl;

public class CRUD_Main {

	public static void main(String[] args) {
		
		CRUD crud = new CRUD();
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
		
		
		ExamplesDAL dal = new ExamplesDAL();
		ExamplesDAL ret =(ExamplesDAL) crud.read2(dal);
		System.out.println(ret.title + " " + ret.exampleId);
		
		TopicsDAL dal1 = new TopicsDAL();
		TopicsDAL ret1 =(TopicsDAL) crud.read2(dal1);
		System.out.println(ret1.title + " " + ret1.topicId + " " + ret1.isHelloWorldTopic);
		
		TopicsDAL dal2 = new TopicsDAL();
		TopicsDAL ret2 =(TopicsDAL) crud.read2(dal2);
		System.out.println(ret2.title + " " + ret2.topicId + " " + ret2.isHelloWorldTopic);
		System.out.println(1 % 12);
	}

}
