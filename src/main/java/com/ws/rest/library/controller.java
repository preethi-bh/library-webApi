package com.ws.rest.library;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class controller {
		Service service=new Service();
		
		@GET
		@Path("/search")
		@Produces(MediaType.APPLICATION_XML)
		public  GenericEntity<List<Model>> getBook(@PathParam("search") String username,@PathParam("search") String subject){
			GenericEntity<List<Model>> m=(GenericEntity<List<Model>>) service.getBookAvail(username,bname);
			return m;
		}
	}
