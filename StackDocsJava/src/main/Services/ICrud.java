package Services;

import java.sql.ResultSet;
import java.util.List;

public interface ICrud {
	
	ResultSet create(String tableName);
	ResultSet read(String tableName);
	ResultSet update(String tableName);
	ResultSet delete(String tableName);

}
