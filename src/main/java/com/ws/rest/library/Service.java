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
		String uname="",stat="";
		try{

			PreparedStatement pst;
			String query="select * from booktrans where bookid=?";
			pst=connection.prepareStatement(query);
			pst.setInt(1,bookid);
			ResultSet rs=pst.executeQuery();

			while(rs!=null&&rs.next()){
				 uname=rs.getString("username");
				stat=rs.getString("status");
			}
			if((uname.equals(username)) && (stat.equals("Available")))
			{
			
			try{
				
				query="update booktrans set status=?,rollno=? where bookid=?";
				pst=connection.prepareStatement(query);
				pst.setString(1,status);
				pst.setString(2,rollno);
				pst.setInt(3,bookid);
			 	result=pst.executeUpdate();
			
				if(result>0){
					
					query="select * from booktrans where bookid=?";
					pst=connection.prepareStatement(query);
					pst.setInt(1,bookid);
					rs=pst.executeQuery();

					while(rs!=null&&rs.next()){
					book.setBname(rs.getString("bname"));
					book.setEdition(rs.getString("edition"));
					book.setSubject(rs.getString("subject"));
					book.setAuthor(rs.getString("author"));
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
		catch(Exception e){
			e.printStackTrace();
		}
		return book;
}

public Books Reserve(String username,String bname,String rollno)
{
	
	Timestamp reserve=new Timestamp(System.currentTimeMillis());
	Books b=new Books();
	int id,res;
	PreparedStatement pst;
	ResultSet rs;
	
	if(bname!=null&&rollno!=null)
	try{
		
		query="select MIN(Renew_Date),bookid from BookTrans where Status='Issued' and bname=? and username=?";
		pst=con.prepareStatement(query);
		pst.setString(1,bname);
		pst.setString(2,username);
		rs=pst.executeQuery();
		while(rs.next()){
			reserve=rs.getTimestamp("min");
			id=rs.getInt("bookid");
		}

		query="update BookTrans set Status='Reserved',Rollno=? where Renew_Date=? and username=?";
		pst=con.prepareStatement(query);
		pst.setString(1,rollno);
		pst.setTimestamp(2,reserve);
		pst.setString(3,username);
		res=pst.executeUpdate();

		if(res>0){
			b.setBookid(id);
			b.setBname(bname);
			b.setRollno(rollno);

			return b;
		}
		else
			return b;		

	}
	catch(Exception e){
		out.println(e);
	}
}
	
}
