package Services;

import java.sql.ResultSet;
import java.util.List;

public interface ICrud {
	
	void create(String tableName);
	ResultSet read(String tableName);
	void update(String tableName, String renamedTableName);
	void delete(String tableName);

}
