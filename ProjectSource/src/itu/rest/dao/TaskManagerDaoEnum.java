package itu.rest.dao;

import itu.rest.model.Cal;
import itu.rest.model.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;



public enum TaskManagerDaoEnum {
instance;



public List<Task> getAllTasks() throws FileNotFoundException, JAXBException{
	//Check this line
	//FileInputStream stream = new FileInputStream("/WEB-INF/task-manager-xml.xml");
	
	FileInputStream stream = new FileInputStream("C:/Users/Efrin Gonzalez/Documents/task-manager-xml.xml");
	// create an instance context class, to serialize/deserialize.
	JAXBContext jaxbContext = JAXBContext.newInstance(Cal.class);
	// Deserialize university xml into java objects.
     Cal cal = (Cal) jaxbContext.createUnmarshaller().unmarshal(stream);

    //With the iterator we can full fill the list that will be sent in response
     ListIterator<Task> listIterator = cal.tasks.listIterator(); 
     List<Task> tasks = new ArrayList<Task>();
         while (listIterator.hasNext()) {
             //Every time there is a new task, it will be stored in the arraylist
             tasks.add(listIterator.next());
         }     
   return tasks;
}
	

}
