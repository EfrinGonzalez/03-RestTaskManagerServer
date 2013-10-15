package itu.rest.resources;

import itu.rest.dao.TaskManagerDaoEnum;
import itu.rest.model.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;

//Will map the resource to the URL todos
@Path("/tasks")
public class TasksResources {

	 // Allows to insert contextual objects into the class, 
	  // e.g. ServletContext, Request, Response, UriInfo
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  
	
	 // Return the list of todos to the user in the browser
	// add this to the path in the browser: rest/tasks
	  @GET
	  @Produces(MediaType.TEXT_XML)	  
	  public List<Task> getTasksBrowser() throws FileNotFoundException, JAXBException {
	    List<Task> tasks = new ArrayList<Task>();
	    
	   tasks = TaskManagerDaoEnum.instance.getAllTasks();
	    return tasks; 
	  }
	
		  
	 @GET
	  @Path("count")
	  @Produces(MediaType.TEXT_PLAIN)
	 // add this to the path in the browser: rest/tasks/count
	 public String getCount() throws FileNotFoundException, JAXBException{
		  int count = TaskManagerDaoEnum.instance.getAllTasks().size();
		    return String.valueOf(count);		
	  }
	 
	 
	// Defines that the next path parameter after tasks is
	  // treated as a parameter and passed to the TaskResources
	  // Allows to type ../rest/tasks/1
	  // 1 will be treaded as parameter todo and passed to TodoResource
	  @Path("{task}")
	 public TaskResources getTask(@PathParam("task") String id){
		 return new TaskResources(uriInfo, request, id);
	 }
	 
}
