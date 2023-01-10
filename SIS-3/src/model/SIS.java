package model;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import bean.EnrollmentBean;
import bean.ListWrapper;
import bean.StudentBean;
import dao.EnrollmentDAO;
import dao.StudentDAO;

public class SIS {

	private static SIS instance;	
	private StudentDAO studentDAO;
	private EnrollmentDAO enrollmentDAO;
	
	public static SIS getInstance() throws ClassNotFoundException {
		
		if (instance == null) {
			instance = new SIS();
			instance.studentDAO = new StudentDAO();
			instance.enrollmentDAO = new EnrollmentDAO();
		}
		
		return instance;
	}
	
	private SIS() {
		
	}
	
	public SIS(StudentDAO studentDAO, EnrollmentDAO enrollmentDAO) {
		this.studentDAO = studentDAO;
		this.enrollmentDAO = enrollmentDAO;
	}
	
	public Map<String, StudentBean> retrieveStudent(String namePrefix, String credit_taken) throws Exception {
		
		if (validate(credit_taken)) {
			return studentDAO.retrieve(namePrefix, Integer.parseInt(credit_taken));
		} else {
			return null;
		}
	}
	
	public Map<String, EnrollmentBean> retrieveEnrollment() throws Exception {
		return enrollmentDAO.retrieve();
	}
	
	public String export(String namePrefix, String credit_taken) throws Exception {
		
		if (validate(credit_taken)) {
			
			List<StudentBean> list = new ArrayList<StudentBean>();
			Map<String, StudentBean> map = studentDAO.retrieve(namePrefix, Integer.parseInt(credit_taken));
			for (Map.Entry<String, StudentBean> e: map.entrySet()) {
				list.add(e.getValue());
			}
			
			ListWrapper lw = new ListWrapper(namePrefix, Integer.parseInt(credit_taken), list);
			
			JAXBContext jc = JAXBContext.newInstance(lw.getClass());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File("C:/Users/nicho/eclipse-workspace/SIS-2/SIS.xsd"));
			marshaller.setSchema(schema);
			
			StringWriter sw = new StringWriter();
			
			sw.write("\n");
			marshaller.marshal(lw, new StreamResult(sw));

			//System.out.println(sw.toString()); // for debugging
			
			//return XML
			return sw.toString();
		
		} else {
			return null;
		}
	}
		
//	public void myImport() throws Exception {
//	
//		JAXBContext jaxbContext = JAXBContext.newInstance(StudentBean.class);
//		Unmarshaller umarshaller = jaxbContext.createUnmarshaller(); 
//		StudentBean sb = (StudentBean) umarshaller.unmarshal(new File("StudentBean.xml"));
//		
//		System.out.println(sb.getSid());
//		System.out.println(sb.getName());
//		System.out.println(sb.getCredit_taken());
//		System.out.println(sb.getCredit_graduate());
//		System.out.println(sb.getCredit_taking());
//	}
	
	public String getAsXML(String name) throws Exception {
		return export(name, "0");
	}
	
	public String create(String sid, String givenname, String surname, String credittaken, String creditgraduate) throws Exception {
		
		if (validate(credittaken) && validate(creditgraduate)) {
			return String.valueOf(studentDAO.insert(sid, givenname, surname, Integer.parseInt(credittaken), Integer.parseInt(creditgraduate)));
		} else {
			return "";
		}
	}
	
	public String delete(String sid) throws Exception {
		return String.valueOf(studentDAO.delete(sid));
	}
	
	private Boolean validate(String p) {
		
		Boolean ok = true;
		
		if (p != "") {
			int temp = Integer.parseInt(p);
			
			if (temp < 0) {
				ok = false;
			}
			
		} else {
			ok = false;
		}
		
		return ok;
	}
}
