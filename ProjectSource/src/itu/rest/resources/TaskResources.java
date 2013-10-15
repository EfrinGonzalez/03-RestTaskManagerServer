package itu.rest.resources;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import itu.rest.dao.TaskManagerDaoEnum;
import itu.rest.model.Task;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;

public class TaskResources {
	@Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  String id;
	  
	  public TaskResources(UriInfo uriInfo, Request request, String id){
		  this.uriInfo = uriInfo;
		    this.request = request;
		    this.id = id;
		  
	  }
	  
	  // for the browser
	  //This works for retreiveing one single task by id
	  /*
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public Task getTaskHTML() throws FileNotFoundException, JAXBException {
		Task task = TaskManagerDaoEnum.instance.getTask(id);  
	 //   Todo todo = TodoDao.instance.getModel().get(id);
	    if(task==null)
	      throw new RuntimeException("Get: Todo with " + id +  " not found");
	    return task;
	  }*/
	// for the browser
		  @GET
		  @Produces(MediaType.TEXT_XML)
		  public List<Task> getSetOfTasksByIdHTML() throws FileNotFoundException, JAXBException {
			  List<Task> task = new ArrayList<Task>();
			   task = TaskManagerDaoEnum.instance.getSetOfTasks(id);
		 //   Todo todo = TodoDao.instance.getModel().get(id);
		    if(task==null)
		      throw new RuntimeException("Get: Todo with " + id +  " not found");
		    return task;

		  
		  /* 
	    
	   tasks = TaskManagerDaoEnum.instance.getAllTasks();
	    return tasks; 
	  }*/
		  }
}
