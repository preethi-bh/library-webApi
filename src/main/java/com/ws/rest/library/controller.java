package com.ws.rest.library;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class controller {
		Service service=new Service();

		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public  List<Books> getBook(@QueryParam("username") String username,@QueryParam("subject") String subject){
			List<Books> m=service.getBookAvail(username,subject);
			return m;
		}

		@PUT
		@Path("/borrow")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Books update(@QueryParam("username") String username,@QueryParam("status") String status,@QueryParam("bookid") int bookid,@QueryParam("rollno") String rollno){
			
			Books result=service.StatusUp(username,status,bookid,rollno);
			return result;
		
		}
		@PUT
		@Path("/reserve")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Books Reserve(@QueryParam("username") String username,@QueryParam("bname") String bname,@QueryParam("rollno") String rollno){	
			Books result=service.Reserve(username,bname,rollno);
			return result;
		
		}
		@GET
		@Path("/login")
		@Produces(MediaType.APPLICATION_JSON)
		public Books login(@QueryParam("username") String username,@QueryParam("rollno") String rollno,@QueryParam("password") String password){
			Books result=service.login(username,rollno,password);
			return result;
		
		}

		@PUT
		@Path("/renew")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Books Renew(@QueryParam("barcode") String barcode){
			Books result=service.Renew(barcode);
			return result;
		}			
}