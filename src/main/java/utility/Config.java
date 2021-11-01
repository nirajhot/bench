package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	Properties pro;
	FileInputStream fis;
	public Config(){
		File src=new File("./Configruation/config.properties");
		try {
			fis	=new FileInputStream(src);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pro=new Properties();
		try {
			pro.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getURL(){
		String applicationURL=pro.getProperty("URL");
		return applicationURL;
	}
	
	public String getLPT(){
		String ltp=pro.getProperty("tagPageTitle");
		return ltp;
	}
	
	public String getCPT(){
		String ctp=pro.getProperty("customerPageTitle");
		return ctp;
	}
	
	public String getCheckOutPT(){
		String checkOuttp=pro.getProperty("checkOutTitle");
		return checkOuttp;
	}
	
	public String getSuccessPT(){
		String stp=pro.getProperty("successPageTitle");
		return stp;
	}
	
	public String getEmail(){
		String getEmail=pro.getProperty("email");
		return getEmail;
	}
	
	public String getPass(){
		String getPass=pro.getProperty("password");
		return getPass;
	}
}