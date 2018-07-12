package Services;

import Models.Business.CrudUpdate;
import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.UpdateTableDTO;

public interface ICrud {
	CreateTableDTO create(Object dal);
	ReadTableDTO read(Object dal);
	UpdateTableDTO update(CrudUpdate params);
	DeleteTableDTO delete(String tableName, String conditionColum, String conditionValue);
}
