package Services;

import java.sql.ResultSet;

import Models.CrudUpdate;
import Models.DTO.ReadTableDTO;
import Models.DTO.UpdateTableDTO;

public interface ICrud {

	void create(String tableName, String[] values);

	ReadTableDTO read(String tableName);

	UpdateTableDTO update(CrudUpdate params);

	void delete(String tableName, String conditionColum, String conditionValue);

}
