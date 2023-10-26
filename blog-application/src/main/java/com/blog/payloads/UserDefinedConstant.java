package com.blog.payloads;

public class UserDefinedConstant 
{
	//these are the constant which will use instead of hard coding every where for easy access

		public static final String PAGE_NUMBER="0";
		public static final String PAGE_SIZE="1";
		public static final String SORT_BY="postId";
		
	
	//constant for the JWT
		public static final String SECRET="jsrk";
		public static final long JWT_VALIDITY=5*60*60*100;
		
	// roles id for role
		public static final Integer ADMIN=1;
		public static final Integer USER=2;

		
}
