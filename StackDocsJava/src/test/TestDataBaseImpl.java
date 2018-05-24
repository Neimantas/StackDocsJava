package test;

import java.sql.Connection;

import Services.Impl.DataBaseImpl;

public class TestDataBaseImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBaseImpl impl = new DataBaseImpl();
		Connection conn = impl.connect();
		
		conn.hashCode();
		impl.close();

	}

}
