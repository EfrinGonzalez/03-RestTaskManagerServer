package itu.rest.resources;

import itu.rest.dao.TaskManagerDaoEnum;
import itu.rest.model.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	  
	
	 // Return the list of tasks to the user in the browser
	// add this to the path in the browser: rest/tasks
	  @GET
	  @Produces(MediaType.TEXT_XML)	  
	  public List<Task> getTasksBrowser() throws FileNotFoundException, JAXBException {
	    List<Task> tasks = new ArrayList<Task>();
	    
	   tasks = TaskManagerDaoEnum.instance.getAllTasks();
	    return tasks; 
	  }
	
		  
	// Return the list of tasks to the user in the applicatoin
		// 
		  @GET
		  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	  
		  public List<Task> getTasks() throws FileNotFoundException, JAXBException {
		    List<Task> tasks = new ArrayList<Task>();
		    
		   tasks = TaskManagerDaoEnum.instance.getAllTasks();
		    return tasks; 
		  }
	  
		  //This function gets the number of tasks within the xml file
	 @GET
	  @Path("count")
	  @Produces(MediaType.TEXT_PLAIN)
	 // add this to the path in the browser: rest/tasks/count
	 public String getCount() throws FileNotFoundException, JAXBException{
		  int count = TaskManagerDaoEnum.instance.getAllTasks().size();
		    return String.valueOf(count);		
	  }
	 
	 
	// Defines that the next path parameter after tasks is
	  // treated as a parameter and passed to the TaskResources class
	  // Allows to type ../rest/tasks/1
	  // 1 will be treaded as parameter task and passed to TaskResource
	 
	  @Path("{task}")
	 public TaskResources getTask(@PathParam("task") String id){
		 return new TaskResources(uriInfo, request, id);
		 //this info is sent to getSetOfTasksByIdHTML in TaskResouces Class
	 }
	 
	  
	  @POST 
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public void newTask(@FormParam("id") String id,
			  			@FormParam("name") String name, 
			  			@FormParam("date") String date,
			  			@FormParam("status") String status,
			  			@FormParam("required") String required,
			  			@FormParam("description") String description,
			  			@FormParam("attendants") String attendants,			  			  
			  			@Context HttpServletResponse servletResponse) throws IOException, JAXBException{
		  
	 TaskManagerDaoEnum.instance.createTask(id, name, date, status, required, description, attendants);
	 servletResponse.sendRedirect("..//create_task.html");
	  
	  }
}
