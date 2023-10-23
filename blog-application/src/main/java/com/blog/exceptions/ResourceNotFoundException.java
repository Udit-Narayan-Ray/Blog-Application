package com.blog.exceptions;


//this is the custom exception for resouces not found
public class ResourceNotFoundException extends RuntimeException
{
	String resourcename;
	String fieldname;
	long fieldvalue;
	public ResourceNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundException(String resourcename, String fieldname, long fieldvalue) {
		super(String.format("%s not found with %s :- %s",resourcename,fieldname,fieldvalue));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}
	
	public String getResourcename() {
		return resourcename;
	}
	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public long getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(long fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	@Override
	public String toString() {
		return "ResourceNotFoundException [resourcename=" + resourcename + ", fieldname=" + fieldname + ", fieldvalue="
				+ fieldvalue + "]";
	}
}
