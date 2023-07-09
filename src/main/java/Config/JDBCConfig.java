package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConfig {
	private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver",
								URL = "jdbc:mysql://localhost:3308/crm_app",
								user = "root",
								passwd = "haihealer0702";
	
		public static Connection getConnection(){
			Connection conn = null;
			try{
				//chỉ định Driver sử dụng
				Class.forName(DB_DRIVER);
				conn = DriverManager.getConnection(URL, user, passwd);
				if(conn == null)
				   System.out.println("Failed to connect.");
			}catch(Exception e){
				System.out.println("Error Connection:"+e.getMessage());
			}
			return conn;
		}	
	}
