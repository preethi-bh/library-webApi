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
				book.setAuthor(rs.getString("author"));
				books.add(book);
		  	 }
		}
		catch(Exception e){
		e.printStackTrace();
		}
	return	books;	
}

	public Books StatusUp(String username,String status,int bookid,String rollno) {
		int result = 0;
		Books book=new Books();
		String uname="",status="";
		try{

			PreparedStatement pst2;
			String query="select * from booktrans where bookid=?";
			pst2=connection.prepareStatement(query);
			pst2.setInt(1,bookid);
			ResultSet rs2=pst2.executeQuery();

			while(rs2!=null&&rs2.next()){
				 uname=rs2.getString("username");
				status=rs2.getString("status");
			}
			if((uname.equals(username)) && (status.equals('Available')))
			{
			
			try{
				PreparedStatement pst;
				query="update booktrans set status=?,rollno=? where bookid=?";
				pst=connection.prepareStatement(query);
				pst.setString(1,status);
				pst.setString(2,rollno);
				pst.setInt(3,bookid);
			 	result=pst.executeUpdate();
			
				if(result>0){
					
					query="select * from booktrans where bookid=?";
					pst2=connection.prepareStatement(query);
					pst2.setInt(1,bookid);
					rs2=pst2.executeQuery();

					while(rs2!=null&&rs2.next()){
					book.setBname(rs2.getString("bname"));
					book.setEdition(rs2.getString("edition"));
					book.setSubject(rs2.getString("subject"));
					book.setAuthor(rs2.getString("author"));
					book.setBookid(rs2.getInt("bookid"));
					}
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
	
			return book;
		
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return book;
}
}
