package Services;

import java.sql.Connection;

public interface IDataBase {
	
	Connection connect();
	void close();
}
