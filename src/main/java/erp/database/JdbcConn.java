package erp.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConn {
	
	public static Connection getConnection() {
		String propertiesPath = "db.properties";
		Connection con = null;
		try(InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPath)){
			Properties prop = new Properties();
			prop.load(in);
			con = DriverManager.getConnection(prop.getProperty("url"), prop);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
