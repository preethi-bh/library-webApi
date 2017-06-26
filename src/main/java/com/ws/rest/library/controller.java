package com.ws.rest.library;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class controller {
		Service service=new Service();

		@GET
		@Path("/search")
		@Produces(MediaType.APPLICATION_XML)
		public  List<Model> getBook(@PathParam("search") String username){
			List<Model> m=service.getBookAvail(username,);
			return m;
		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Model> getAll(){
			List<Model> m1=service.getAllBooks();
			return m1;
	}
}