package Services.Impl;

import java.lang.reflect.Field;

import Services.ISelectBuilder;

public class SelectBuilder implements ISelectBuilder {

	@Override
	public String build(Object inputDAL) {

		Class input = inputDAL.getClass();
		String dalName = input.getSimpleName();
		// remove DAL from class name and get db table name
		String tableName = dalName.replaceAll("DAL", "");

		String selectTemplate = "SELECT * FROM ";
		selectTemplate += tableName + " WHERE ";

		Field[] tableColumns = input.getFields();

		for (Field column : tableColumns) {

			try {

				Object value = column.get(inputDAL);
				//super duper mega if'as
				if (value instanceof Integer && (Integer) value != 0 
						
						|| (value instanceof Byte) && ((Byte) value != 0)
						
						|| !(value instanceof Integer) && !(value instanceof Byte) && value != null) {
					
					selectTemplate += column.getName() + "=" + value + " AND ";
//					selectTemplate = selectTemplate.substring(0, selectTemplate.length()-5);
				} 

			} catch (IllegalArgumentException | IllegalAccessException e) {

				e.printStackTrace();

			}
//			selectTemplate = selectTemplate.substring(0, selectTemplate.length()-5);

		}
		
		
		//remove last " AND "
		

		return selectTemplate + ";";
	}

}
