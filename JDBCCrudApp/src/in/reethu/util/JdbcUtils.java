package in.reethu.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtils {

	public static Connection conn;

	public static Connection getJdbcConnection() throws SQLException {
		HikariConfig config = new HikariConfig("src\\in\\reethu\\properties\\db.properties");
		HikariDataSource datasource = new HikariDataSource(config);
		conn = datasource.getConnection();
		return conn;
	}
	
	
	public static Connection getPhysicalConnectio() throws IOException, SQLException {
		FileInputStream fis = new FileInputStream("src\\in\\reethu\\properties\\db.properties");
		Properties props = new Properties();
		props.load(fis);

		conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"),
				props.getProperty("password"));
		return conn;
	}

}
