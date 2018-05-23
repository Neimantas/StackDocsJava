

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.LanguageCallback;

public class CRUD_Main {

	public static void main(String[] args) {

		CRUD crud = new CRUD();
		List<LanguageTagDAL> listas = new ArrayList();
		
		//check if i getting List with info.
		listas = crud.readLanguageDB();
		LanguageTagDAL langObj = new LanguageTagDAL();
		for (int i = 0; i < listas.size(); i++) {
			System.out.println(
					langObj.languageId + "; " + langObj.tag + "; " + langObj.title + "; " + langObj.helloWorldTopicId);
		}

	}

}