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
	public List<Books> getBookAvail(String username,String subject){
		List<Books> books=new ArrayList<Books>();
		try{
			PreparedStatement pst;
			String query="select * from booktrans where username=? and subject=? and status='Available'";
			pst=connection.prepareStatement(query);
			pst.setString(1,username);
			pst.setString(2,subject);
			ResultSet rs=pst.executeQuery();

			while(rs!=null&&rs.next()){
				Books book=new Books();
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

	public Books StatusUp(String status,int bookid,String rollno) {
		int result = 0;
		Books book=new Books();
		try{
			PreparedStatement pst;
			String query="update booktrans set status=?,rollno=? where bookid=?";
			pst=connection.prepareStatement(query);
			pst.setString(1,status);
			pst.setString(2,rollno);
			pst.setInt(3,bookid);
			 result=pst.executeUpdate();
			
			if(result>0){
			PreparedStatement pst2;
			String query="select * from booktrans where bookid=?";
			pst2=connection.prepareStatement(query);
			pst2.setString(1,bookid);
			ResultSet rs2=pst2.executeQuery();

			while(rs!=null&&rs.next()){
				book.setBname(rs.getString("bname"));
				book.setEdition(rs.getString("edition"));
				book.setSubject(rs.getString("subject"));
				book.setBookid(rs.getInt("bookid"));
			}
			}
				
			
		}
		catch(Exception e){
		e.printStackTrace();
		}
	
		return book;
	}
}
