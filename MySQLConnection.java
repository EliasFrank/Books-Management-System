import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
	private static Connection con = null;
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/bookms?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
	private static String name = "root";
	private static String password = "123";
	static {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return con;
	}
}
