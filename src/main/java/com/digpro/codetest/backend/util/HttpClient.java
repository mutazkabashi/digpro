package com.digpro.codetest.backend.util;

import java.io.IOException;

/**
 * 
 * Http Client Interface which specify the specification for 
 * the Http client Implemenation. it provides the basic http's Call/methods
 * Operations such as Get and Post
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */
public interface HttpClient {
	
	/**
	 *  This method used to make http's Get call/request to remote URL 
	 * @param url url of the remote servlet/ws
	 * @return
	 * @throws IOException
	 */
	String doGetRequest(String url) throws IOException ;
	
	
	 

}
