package com.infinitywar.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.infinitywar.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.infinitywar.soap.webservices.soapcoursemanagement.soap.exeption.NotFoundException;
import com.infinitywar.soap.webservices.soapcoursemanagement.soap.service.DetailsService;
import com.infinitywar.soap.webservices.soapcoursemanagement.soap.service.DetailsService.Status;
import com.sun.xml.internal.ws.wsdl.writer.document.Service;

@Endpoint
public class DetailsEndpoint {
	
	@Autowired
	DetailsService servise;

	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// http://infinitywar.com/courses
	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://infinitywar.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest
	(@RequestPayload GetCourseDetailsRequest request) {
		
		Course course = servise.findById(request.getId());
		
		if (course == null)
			throw new NotFoundException("Invalid Course Id " + request.getId());

		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		response.setCourseDetails(mapCourse(course));
		
		return response;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		
		for(Course course:courses)
		{
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		
		courseDetails.setName(course.getName());
		
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	@PayloadRoot(namespace = "http://infinitywar.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processАllCourseDetailsRequest
	(@RequestPayload GetAllCourseDetailsRequest request) {
		
		List <Course>  courses = servise.findAll();
		
		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace = "http://infinitywar.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest
	(@RequestPayload DeleteCourseDetailsRequest request) {
		
		Status status = servise.deleteById(request.getId());
		
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		
		return response;
	}

	private com.infinitywar.courses.Status mapStatus(Status status) {
		if(status==Status.FAILURE) {
			return com.infinitywar.courses.Status.FAILURE;
		}
		return com.infinitywar.courses.Status.SUCCESS;
	}

}