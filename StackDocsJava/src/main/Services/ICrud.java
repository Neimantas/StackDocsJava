package Services;

import java.util.List;

public interface ICrud {
	
	List<Object> create(String tableName);
	List<Object> read(String tableName);
	List<Object> update(String tableName);
	List<Object> delete(String tableName);

}
