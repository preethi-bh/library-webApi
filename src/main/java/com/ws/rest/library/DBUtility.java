package com.ws.rest.library;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
	private static Connection connection=null;
	public static Connection getConnection(){
		if(connection!=null)
			return connection;
		else{
		   try{
			Class.forName("org.postgresql.Driver");
			String dbUrl = System.getenv("JDBC_DATABASE_URL");
			    return DriverManager.getConnection(dbUrl);
		     }
		    catch(Exception e){
			e.printStackTrace();
		    }
			return connection;
		}
	}	
}
