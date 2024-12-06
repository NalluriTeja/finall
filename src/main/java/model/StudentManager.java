package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.TypedQuery;

public class StudentManager {
	public String addStudent(Student S) throws Exception 
	{
		SessionFactory SF = new Configuration().configure().buildSessionFactory();
		Session SES = SF.openSession();
		SES.getTransaction().begin();
		SES.persist(S);
		SES.getTransaction().commit();
		
		SES.close();
		SF.close();
		
		return "Data has been saved succesfully";
	}
	public String updateStudent(Student S) throws Exception
	{
		SessionFactory SF = new Configuration().configure().buildSessionFactory();
		Session SES = SF.openSession();
		SES.getTransaction().begin();
		Student tmp =SES.find(Student.class, S.getSid());
		tmp.setSname(S.getSname());
		tmp.setSdept(S.getSdept());
		SES.merge(tmp);
		SES.getTransaction().commit();
		
		SES.close();
		SF.close();
		return "Data has been updated sucessfully";
	}

	public String deleteStudent(int sid) {
		SessionFactory SF = new Configuration().configure().buildSessionFactory();
		Session SES = SF.openSession();
		SES.getTransaction().begin();
	    Student tmp =SES.find(Student.class, sid);
	    SES.remove(tmp);
		SES.getTransaction().commit();
		
		SES.close();
		SF.close();
		
		return "Data has been deleted succesfully";
	}
	public List<Student> readStudent() throws Exception
	{
		List<Student> slist =null;
		SessionFactory SF = new Configuration().configure().buildSessionFactory();
		Session SES = SF.openSession();
		SES.getTransaction().begin();
		TypedQuery<Student> qry = SES.createQuery("select S from Student S", Student.class);
		slist = qry.getResultList();
		SES.getTransaction().commit();
		SES.close();
		SF.close();
		
		return slist;
	}
	
}