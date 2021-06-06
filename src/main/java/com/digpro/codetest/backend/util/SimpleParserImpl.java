package com.digpro.codetest.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * A Simple Parser Implemenation to {@link Parser}
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */
@Component
public class SimpleParserImpl implements Parser{
	
    private static final Logger LOG = LoggerFactory.getLogger(SimpleParserImpl.class);


	@Override
	public  String findByPattern(String text, String pattern) throws NullPointerException {
		if(text==null || pattern==null) {
			LOG.error("Error Occured white parsing data",new NullPointerException());
		    //TODO Exception message should be in config file(application.properties)
			throw new  NullPointerException("One of the findByPattern() methods paramets is null");
		}
		else if(text.indexOf(pattern)!=-1) {
		String result = text.substring(text.indexOf(pattern));
		return result.trim();
		}
		else {
			return "";
		}
	}
	

}
