package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	public static Properties getMySqlProperties(){
		Properties prop = new Properties();
		File jarPath = new File(PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String propertiesPath = jarPath.getParentFile().getAbsolutePath();
		try {
			prop.load(new FileInputStream(propertiesPath + "/config/mysql.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static Properties getOracleProperties(){
		Properties prop = new Properties();
		File jarPath = new File(PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String propertiesPath = jarPath.getParentFile().getAbsolutePath();
		try {
			prop.load(new FileInputStream(propertiesPath + "/config/oracle.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
