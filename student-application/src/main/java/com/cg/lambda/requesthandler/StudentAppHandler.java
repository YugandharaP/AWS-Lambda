package com.cg.lambda.requesthandler;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cg.lambda.config.DynamoFactory;
import com.cg.lambda.exceptions.ResourceNotFoundException;
import com.cg.lambda.model.RequestClass;
import com.cg.lambda.model.ResponseClass;
import com.cg.lambda.model.Student;

public class StudentAppHandler implements RequestHandler<RequestClass, ResponseClass> {

	@Override
	public ResponseClass handleRequest(RequestClass request, Context context) {
		DynamoDBMapper mapper = DynamoFactory.dynamoDBMapper();
		Student student = null;
		ResponseClass response = null;
		switch (request.getHttpMethod()) {
		case "GET":
			student = mapper.load(Student.class, request.getId());
			if (student == null) {
				try {
					throw new ResourceNotFoundException("Student Data Of id: " + request.getId());
				} catch (ResourceNotFoundException e) {
					response = new ResponseClass();
					response.setMsg(e.getMessage());
					response.setStatus("404");
					return response;
				}
			}
			response = new ResponseClass();
			response.setMsg("successfull: get data" + student.toString());
			response.setStatus("200");
			return response;

		case "POST":
			student = request.getStudent();
			mapper.save(student);
			response = new ResponseClass();
			response.setMsg("successfull: stored data" + student.toString());
			response.setStatus("201");
			return response;
		
		case "DELETE":
			student= mapper.load(Student.class, request.getId());
			if (student == null) {
				try {
					throw new ResourceNotFoundException("Student Data Of id: " + request.getId());
				} catch (ResourceNotFoundException e) {
					response = new ResponseClass();
					response.setMsg(e.getMessage());
					response.setStatus("404");
					return response;
				}
			}
			mapper.delete(student);
			response = new ResponseClass();
			response.setMsg("successfull: deleted");
			response.setStatus("200");
			return response;
		default:
		}
		return null;
	}
}