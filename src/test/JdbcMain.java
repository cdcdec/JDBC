package test;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.File;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class JdbcMain {
	public static void main(String[] args) {
		launch(JdbcMain.class);
		Scanner scanner = new Scanner(System.in);
		System.out.println("================================================");
		System.out.println("=====1===輸入NAME================================");
		System.out.println("=====2===輸入MACA================================");
		System.out.println("================================================");
		int num=0;
		String name;
		String mac;
		num = scanner.nextInt();
        switch(num){
        case 1:
        	System.out.println("請輸入用戶名:");
        	name=scanner.next();
        	String sql="select userno,password,macno,TB_MCTM_USER_TERMINAL.TER_LOGINNO,TB_MCTM_USER_TERMINAL.TERMINALID from C_USERINFO,TB_MCTM_USER_TERMINAL where C_USERINFO.userno=TB_MCTM_USER_TERMINAL.userid and C_USERINFO.userno= ? ";
        	ResultSet result=JDBCOracleUtil.getJDBCUtil().executeQuery(sql, name);
        	StringBuilder stringBuilder=new StringBuilder();
        	try {
				while(result.next()){
					stringBuilder.append("用戶名:");
					stringBuilder.append(result.getString(1));
					stringBuilder.append(",密码:");
					stringBuilder.append(result.getString(2));
					stringBuilder.append(",mac:");
					stringBuilder.append(result.getString(3));
					stringBuilder.append(",loginno:");
					stringBuilder.append(result.getString(4));
					stringBuilder.append(",terminalid:");
					stringBuilder.append(result.getString(5));
				}
				if(stringBuilder.length()>0){
					System.out.println(stringBuilder.toString());
				}else{
					System.out.println("查不到相关的信息");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					result.close();
					JDBCOracleUtil.getJDBCUtil().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	break;
        case 2:
        	System.out.println("請輸入MAC地址:");
        	mac=scanner.next();
        	String sqlMac="select userno,password,macno,TB_MCTM_USER_TERMINAL.TER_LOGINNO,TB_MCTM_USER_TERMINAL.TERMINALID from C_USERINFO,TB_MCTM_USER_TERMINAL where C_USERINFO.userno=TB_MCTM_USER_TERMINAL.userid and TB_MCTM_USER_TERMINAL.macno= ? ";
        	ResultSet resultMac=JDBCOracleUtil.getJDBCUtil().executeQuery(sqlMac, mac);
        	StringBuilder stringBuilderMac=new StringBuilder();
        	try {
				while(resultMac.next()){
					stringBuilderMac.append("用戶名:");
					stringBuilderMac.append(resultMac.getString(1));
					stringBuilderMac.append(",密码:");
					stringBuilderMac.append(resultMac.getString(2));
					stringBuilderMac.append(",mac:");
					stringBuilderMac.append(resultMac.getString(3));
					stringBuilderMac.append(",loginno:");
					stringBuilderMac.append(resultMac.getString(4));
					stringBuilderMac.append(",terminalid:");
					stringBuilderMac.append(resultMac.getString(5));
				}
				if(stringBuilderMac.length()>0){
					System.out.println(stringBuilderMac.toString());
				}else{
					System.out.println("查不到相关的信息");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					resultMac.close();
					JDBCOracleUtil.getJDBCUtil().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	break;
        }
        //d847106ff6c3
	}
	
	public static void launch(Class<?> clz){
		String batFileName=clz.getCanonicalName()+"_launch.bat";
		Console console = System.console();
		// If the application has a console...
		if (console != null ) {
			System.out.println("console！！！!= null");
			
		}else if (!GraphicsEnvironment.isHeadless()) {
			System.out.println("####console== null");
			// Get OS
			String os = System.getProperty("os.name").toLowerCase();
			// If OS is a windows OS
			if (os.contains("windows")) {
				// Change "main" to this class's name
				try {
					File JarFile = new File(clz.getProtectionDomain().getCodeSource().getLocation().toURI());
					// Get a printWriter object to make a batch file
					PrintWriter out = new PrintWriter(new File(batFileName));
					// turn echo off for batch file
					out.println("@echo off");
					// replace "name of program" with your program's name
					out.println("title Run Jar");
					// launches itself in its own location
					out.println("java -jar " + JarFile.getPath());
					out.close(); // saves file
					Runtime rt = Runtime.getRuntime(); // gets runtime
					rt.exec("cmd /c start "+batFileName); // executes .bat
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Exit program, so only instance of program with command line
				// runs!
				//System.exit(0);
			}
		}
	}

}
