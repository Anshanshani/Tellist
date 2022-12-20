package edu.jit.utils;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static Properties prop = null;
	static{
		prop = new Properties();
		try {
			prop.load(
					new FileInputStream(JDBCUtils.class.getClassLoader().getResource("conf.properties").toURI().getPath()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws Exception{
		Class.forName(prop.getProperty("driver"));
		return DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"));
	}
	
	public static void close(Connection conn,Statement stat,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				stat=null;
			}
		}if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
		
		
		
	}
		
	
}
