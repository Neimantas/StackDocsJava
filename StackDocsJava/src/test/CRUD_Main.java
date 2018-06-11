

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
		System.out.println(crud.read(new ExamplesDAL()).isSuccess());
		System.out.println(crud.read(new LanguageTagsDAL()).isSuccess());
		System.out.println(crud.read(new TopicsDAL()).isSuccess());
		System.out.println(crud.read(new String()).isSuccess());
		


		
//####################################################	
//		UpdateTableDTO updDTO = new UpdateTableDTO();
//		CrudUpdate params = new CrudUpdate();
//
//		params.conditionColumName = "a";
//		
//		updDTO = crud.update(params);
//####################################################
		
		
		

		
	}

}
