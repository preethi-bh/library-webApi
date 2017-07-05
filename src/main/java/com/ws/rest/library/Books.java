package com.ws.rest.library;

import java.sql.Date;

public class Books {
		  String username;
		  String author;
		  String bname;
		  String edition;
		  String subject;
		  String rollno;
		 int mobileno;
		  Date issue_date;
		  Date renew_date;
		  String status;
		  int bookid;
		  String password;
		
				 

		public void setUsername(String username) {
			this.username = username;
		}

		public void setAuthor(String author){
			this.author=author;
		}
		public void setPassword(String password){
			this.password=password;
		}
		
		public void setBname(String bname) {
			this.bname = bname;
		}
		
		public void setEdition(String edition) {
			this.edition = edition;
		}
		
		public void setSubject(String subject) {
			this.subject = subject;
		}
		
		public void setRollno(String rollno) {
			this.rollno = rollno;
		}
		
		public void setIssue_date(Date issue_date) {
			this.issue_date = issue_date;
		}
		
		public void setRenew_date(Date renew_date) {
			this.renew_date = renew_date;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
		
		public void setBookid(int bookid) {
			this.bookid = bookid;
		}
		public String getUsername() {
        	return username;	
		}

    		

  	  public String getBname() {
        return bname;
    }

    public String getEdition() {
        return edition;
    }

    public String getSubject() {
        return subject;
    }

    public int getBookid() {
        return bookid;
    }

    public Date getRenew_date() {
        return renew_date;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public String getStatus() {
        return status;
    }

    public String getRollno() {
        return rollno;
    }

    public String getAuthor() {
        return author;
    }
		
	
}
