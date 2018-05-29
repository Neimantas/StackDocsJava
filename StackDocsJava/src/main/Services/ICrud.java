package Services;

import java.sql.ResultSet;

import Models.DTO.ReadTableDTO;

public interface ICrud {

	void create(String tableName, String[] values);

	ReadTableDTO read(String tableName);

	void update(String tableName, String changeValueOfColum, String changeValueTO, String conditionColumName,
			String conditionChangeWhereValueIsEqual);

	void delete(String tableName, String conditionColum, String conditionValue);

}
