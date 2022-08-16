package application;
import java.sql.*;
public class SqliteConnection {

	public static Connection connector() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection con=DriverManager.getConnection("jdbc:sqlite:myDb.db");
		return con;
		
	}
	
	
}
