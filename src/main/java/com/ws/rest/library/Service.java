package com.ws.rest.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Service {
private Connection connection;
	
	public Service(){
		connection=DBUtility.getConnection();
	}	
	public List<Model> getBookAvail(String username,String bname){
		List<Model> books=new ArrayList<Model>();
		try{
			PreparedStatement pst;
			String query="select * from booktrans where username=? and subject=? and status='Available'";
			pst=connection.prepareStatement(query);
			pst.setString(1,username);
			pst.setString(2,subject);
			ResultSet rs=pst.executeQuery();

			while(rs!=null&&rs.next()){
				Model book=new Model();
				book.setBname(rs.getString("bname"));
				book.setEdition(rs.getString("edition"));
				book.setSubject(rs.getString("subject"));
				book.setBookid(rs.getInt("bookid"));
				books.add(book);
		  	 }
		}
		catch(Exception e){
		e.printStackTrace();
		}
	return	books;	
}
}
