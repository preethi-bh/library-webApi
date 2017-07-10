package com.ws.rest.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Service {
private Connection connection;
	
	public Service(){
		connection=DBUtility.getConnection();
	}
	public Books login(String username,String rollno,String password){
			Books b=new Books();
		try{
			PreparedStatement pst;
			String query="select * from student where username=? and rollno=? and password=?";
			pst=connection.prepareStatement(query);
			pst.setString(1,username);
			pst.setString(2,rollno);
			pst.setString(3,password);
			ResultSet rs=pst.executeQuery();
			
			while(rs!=null&&rs.next()){ 
					b.setUsername(rs.getString("name")); 				
		  	 }
		}
		catch(Exception e){
		e.printStackTrace();
		}
	return	b;	//Returns null if student is not present else returns rollno as successfull response
		
	}	

	public Books Renew(String barcode){
			Books b=new Books();
			int res;
		try{
			PreparedStatement pst;
			String query="update booktrans set Renew_Date=now()+ interval '15 days' where barcode=?";
			pst=connection.prepareStatement(query);
			pst.setString(1,barcode);
			res=pst.executeUpdate();
			
			if(res>0)
			query="select Renew_Date from booktrans where barcode=?";
			pst=connection.prepareStatement(query);
			pst.setString(1,barcode);
			ResultSet rs=pst.executeQuery();
			
			while(rs!=null){
				b.setRenew_date(rs.getTimestamp("renew_date"));
				return b;
			}
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return b;
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
	int id=0,res;
	PreparedStatement pst;
	ResultSet rs;
	
	if(bname!=null&&rollno!=null)
	try{
		String query="select * from booktrans where username=? and bname=? and Status='Available'";
		pst=connection.prepareStatement(query);
		pst.setString(1,username);
		pst.setString(2,bname);
		rs=pst.executeQuery();
		if(rs!=null){
			b.setBookid(id);//Returns bookid as 0 to indicate books are available
			return b;
		}
			
		else{
		query="select MIN(Renew_Date),bookid from BookTrans where Status='Issued' and bname=? and username=?";
		pst=connection.prepareStatement(query);
		pst.setString(1,bname);
		pst.setString(2,username);
		rs=pst.executeQuery();
		while(rs.next()){
			reserve=rs.getTimestamp("min");
			id=rs.getInt("bookid");
		}

		query="update BookTrans set Status='Reserved',Rollno=? where Renew_Date=? and username=?";
		pst=connection.prepareStatement(query);
		pst.setString(1,rollno);
		pst.setTimestamp(2,reserve);
		pst.setString(3,username);
		res=pst.executeUpdate();

		if(res>0){
			b.setBookid(id);
			b.setBname(bname);

			return b;	//Returns a successfull response of reserved book
		}
		}
				

	}
	catch(Exception e){
		e.printStackTrace();
	}
	return b;	//Returns null set indicating error in the query params
}
	
}
