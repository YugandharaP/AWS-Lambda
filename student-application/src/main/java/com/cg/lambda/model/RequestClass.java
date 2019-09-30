package com.cg.lambda.model;

public class RequestClass {
	private String httpMethod;
	private String id;
	private Student student;

	public RequestClass(){
		
	}
	
	
	public RequestClass(String httpMethod, String id, Student student) {
		this.httpMethod = httpMethod;
		this.id = id;
		this.student = student;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Request [httpMethod=" + httpMethod + ", id=" + id + ", student=" + student + "]";
	}
}
