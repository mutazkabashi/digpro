package com.digpro.codetest.backend.service;

import java.util.List;

import com.digpro.codetest.backend.domain.Coordinate;
import com.digpro.codetest.backend.exception.CodeTestException;

/**
 * Service class which provides services/methods to  the front end such as
 * get New Coordinates. it acts like an interface between Front end and the back end
 * 
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */
public interface CoordinateService {
	
	/**
	 * Retrieves New Generated coordinates from the back-end 
	 * @return
	 */
	public List<Coordinate> getNewCoordiantes() throws CodeTestException;

}
