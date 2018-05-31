package Services;

import java.sql.ResultSet;

import Models.CrudUpdate;
import Models.DTO.CreateTableDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.UpdateTableDTO;

public interface ICrud {

	CreateTableDTO create(String tableName, String[] values);

	ReadTableDTO read(String tableName);

	UpdateTableDTO update(CrudUpdate params);

	void delete(String tableName, String conditionColum, String conditionValue);

}
