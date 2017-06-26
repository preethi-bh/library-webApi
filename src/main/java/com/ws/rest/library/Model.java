package com.ws.rest.library;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Model {
		  String username;
		  String bname;
		  String edition;
		  String subject;
		  String rollno;
		  int mobileno;
		  Date issue_date;
		  Date renew_date;
		  String status;
		  int bookid;
		
	Model(){
	}
		  
		  public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getBname() {
			return bname;
		}
		public void setBname(String bname) {
			this.bname = bname;
		}
		public String getEdition() {
			return edition;
		}
		public void setEdition(String edition) {
			this.edition = edition;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getRollno() {
			return rollno;
		}
		public void setRollno(String rollno) {
			this.rollno = rollno;
		}
		public int getMobileno() {
			return mobileno;
		}
		public void setMobileno(int mobileno) {
			this.mobileno = mobileno;
		}
		public Date getIssue_date() {
			return issue_date;
		}
		public void setIssue_date(Date issue_date) {
			this.issue_date = issue_date;
		}
		public Date getRenew_date() {
			return renew_date;
		}
		public void setRenew_date(Date renew_date) {
			this.renew_date = renew_date;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getBookid() {
			return bookid;
		}
		public void setBookid(int bookid) {
			this.bookid = bookid;
		}
		
	@Override
	 public String toString(){
	    return "col name:"+username+" Bname:"+bname+" Edition:"+edition+" Subject:"+subject;

	  }
}
