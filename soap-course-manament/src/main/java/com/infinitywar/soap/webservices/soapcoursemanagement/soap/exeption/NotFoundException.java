package com.infinitywar.soap.webservices.soapcoursemanagement.soap.exeption;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM, customFaultCode="{http://in28minutes.com/courses}001_COURSE_NOT_FOUND")
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4030975632697115246L;

	public NotFoundException(String message) {
		super(message);

	}

	
}
