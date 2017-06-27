package com.ws.rest.library;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class controller {
		Service service=new Service();

		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public  List<Model> getBook(@QueryParam("username") String username,@QueryParam("subject") String subject){
			List<Model> m=service.getBookAvail(username,subject);
			return m;
		}

		@PUT
		@Path("/{update}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces("text/plain")
		public int update(@QueryParam("status") String status,@QueryParam("bookid") int bookid,@QueryParam("rollno") String rollno){
			
			int result=service.StatusUp(status,bookid,rollno);
			return result;
		
}
}