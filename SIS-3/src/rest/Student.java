package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.SIS;

@Path("student") //this is the path of the resource
public class Student {
	
	@GET
	@Path("/read/")
	@Produces("text/plain")
	public String getStudent(@QueryParam("studentName")String name) throws Exception {
		return SIS.getInstance().getAsXML(name);
	}
	
	@POST
	@Path("/create/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String createStudent(@QueryParam("sid")String sid, @QueryParam("givenName")String givenname, @QueryParam("surName")String surname, @QueryParam("creditTaken")String credittaken, @QueryParam("creditGraduate")String creditgraduate) throws Exception {
		return "insertedRows: " + SIS.getInstance().create(sid, givenname, surname, credittaken, creditgraduate);
	}
	
	@DELETE
	@Path("/delete/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String delete(@QueryParam("sid")String sid) throws Exception {	
		return "deletedRows: " + SIS.getInstance().delete(sid);
	}
}
