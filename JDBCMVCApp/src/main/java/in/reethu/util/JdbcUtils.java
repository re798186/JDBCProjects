package in.reethu.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtils {

	public static Connection conn;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws SQLException {
		HikariConfig config = new HikariConfig(
				"C:\\Reethu\\JavaApplications\\JDBCMVCApp\\src\\main\\java\\in\\reethu\\properties\\db.properties");
		HikariDataSource datasource = new HikariDataSource(config);
		conn = datasource.getConnection();
		//conn = getPhysicalConnectio();
		return conn;
	}

	public static Connection getPhysicalConnectio() throws  SQLException {
		try {
		FileInputStream fis = new FileInputStream("C:\\Reethu\\JavaApplications\\JDBCMVCApp\\src\\main\\java\\in\\reethu\\properties\\db.properties");
		Properties props = new Properties();
			props.load(fis);

		conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"),
				props.getProperty("password"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

}