package com.infinitywar.soap.webservices.soapcoursemanagement.soap.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Spring;

import org.springframework.stereotype.Component;

import com.infinitywar.soap.webservices.soapcoursemanagement.soap.bean.Course;

@Component
public class DetailsService {
	
	public enum Status {
		SUCCESS, FAILURE;
	}
	
	private static List<Course>	courses = new ArrayList<>();
		
	static {
		Course course1 = new Course(1, "Car", "Light car veichle, somewhat good driver ");
		courses.add(course1);
		
		Course course2 = new Course(2, "Truck", "Heavy veichle, somewhat slow and danger!!! ");
		courses.add(course2);
		
		Course course3 = new Course(3, "Spring Boot", "6K Students");
		courses.add(course3);
		
		Course course4 = new Course(4, "Maven", "Most popular maven course on internet");
		courses.add(course4);
		
		
	}	
	
	
	//course - 1
	public Course findById(int id)
		{
			for (Course course:courses) 
			{
				if(course.getId()==id) 
				{
					return course;
				}
			}
			
			return null;
		}
		
	//courses
	public List<Course> findAll()
		{
			return courses;
		}
	
	//deletecource
	public Status deleteById(int id)
		{
			
			Iterator<Course> iterator = courses.iterator();
			while (iterator.hasNext())
			{
				Course course = iterator.next();
				if(course.getId()==id)
				{
					iterator.remove();
					
					return Status.SUCCESS;
				}
			}
			return Status.FAILURE;
		}
		//updating course & new course
}
