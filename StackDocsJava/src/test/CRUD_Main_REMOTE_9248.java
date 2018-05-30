

import java.util.ArrayList;
import java.util.List;

import Models.CrudUpdate;
import Models.DTO.UpdateTableDTO;
import Services.IHigherService;
import Services.Impl.CRUD;
import Services.Impl.HigherServiceImpl;

public class CRUD_Main {

	public static void main(String[] args) {
		
		CRUD crud = new CRUD();
//			System.out.println(crud.read("LanguageTags"));
//
//		IHigherService serv = new HigherServiceImpl();
//		
//		System.out.println(serv.readLanguageTag("Bla").size());
//		System.out.println(serv.readTopics("").size());
//		System.out.println(serv.readExamples("").size());
		
		
		UpdateTableDTO updDTO = new UpdateTableDTO();
		CrudUpdate params = new CrudUpdate();

		params.conditionColumName = "a";
		
		updDTO = crud.update(params);
		
		
		
		
	}

}
