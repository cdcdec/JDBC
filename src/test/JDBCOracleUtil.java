package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCOracleUtil {
	//连接对象
	private Connection connection;
	//维护一个本类型的对象
	private static JDBCOracleUtil jdbcUtil;
	//Statement对象,可以执行SQL语句并返回结果
	private Statement stmt;
	private PreparedStatement preparedStatement;
	
	//私有构造器
	private JDBCOracleUtil() {
		try {
			Properties prop=PropertiesUtil.getOracleProperties();
			//初始化JDBC驱动并让驱动加载到jvm中
			Class.forName(prop.getProperty("oracle_class"));
			//创建数据库连接
			connection = DriverManager.getConnection(prop.getProperty("oracle_connection"), prop.getProperty("oracle_connection_name"),prop.getProperty("oracle_connection_password"));
			//创建Statement对象
			stmt =connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//提供一个静态方法返回本类的实例
	public static JDBCOracleUtil getJDBCUtil() {
		//如果本类所维护jdbcExecutor属性为空,则调用私有的构造器获得实例
		if (jdbcUtil == null) {
			jdbcUtil = new JDBCOracleUtil();
		}
		return jdbcUtil;
	}
	/**
	 * 计算数据总数
	 * @param sql
	 * @return
	 */
	public Integer count(String sql) {
		try {
			//利用Statement对象执行参数的sql
			ResultSet result = stmt.executeQuery(sql);
			result.next();
			Integer count = result.getInt(1);
			result.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 执行一句查询的sql
	 */
	public ResultSet executeQuery(String sql) {
		try {
			//利用Statement对象执行参数的sql
			ResultSet result = stmt.executeQuery(sql);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 执行一句查询的sql
	 */
	public ResultSet executeQuery(String sql,String str) {
		try {
			//利用Statement对象执行参数的sql
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, str);  
			ResultSet result = preparedStatement.executeQuery();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(){
		try {
			if(stmt!=null & !stmt.isClosed()){
				stmt.close();
			}
			if(preparedStatement!=null & !preparedStatement.isClosed()){
				preparedStatement.close();
			}
			if(connection!=null & !connection.isClosed()){
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
