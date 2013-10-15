package itu.rest.resources;

import itu.rest.dao.TaskManagerDaoEnum;
import itu.rest.model.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

//Will map the resource to the URL todos
@Path("/tasks")
public class TasksResources {

	
	 @GET
	  @Path("count")
	  @Produces(MediaType.TEXT_PLAIN)
	 public String getCount() throws FileNotFoundException, JAXBException{
		  int count = TaskManagerDaoEnum.instance.getAllTasks().size();
		    return String.valueOf(count);
		
	  }
}
