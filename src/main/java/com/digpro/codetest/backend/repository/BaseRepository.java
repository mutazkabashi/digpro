package com.digpro.codetest.backend.repository;

import java.io.IOException;
import java.util.List;

import com.digpro.codetest.backend.domain.Coordinate;

/**
 * 
 * Repository or Data source Adaptor which is used to retrieve data from Data source such as Database /RestApi or servlet
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */
public interface BaseRepository {
	
	/**
	 * This method returns All coordinates from a data source (in this project it is a servlet but it could be a database also.
	 * in another word, it  depends on the  Repository/Datasource adaptor Implementation)
	 * 
	 * @return
	 */
	public List<Coordinate> getAll() throws Exception;

}
