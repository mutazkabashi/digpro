package com.digpro.codetest.backend.util;

/**
 * A Parser, Which parse a String (X), and do various operations on a text like
 * finding a string in a text, add string to a text ,,etc
 * 
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */
public interface Parser {

	/**
	 * return a Substring from text if the text contains a string with a
	 * specific/specified pattern
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 * @throws NullPointerException
	 */
	public String findByPattern(String text, String pattern) throws NullPointerException;

}
