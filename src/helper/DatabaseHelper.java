package helper;

import java.sql.Connection;

import java.sql.DriverManager;

public class DatabaseHelper {
	public static Connection openConnection() throws Exception{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String Url = "jdbc:sqlserver://localhost;databaseName=UserData;user=sa;password=01022003";
		Connection conn = DriverManager.getConnection(Url);
		return conn;
	}
}
