package com.digpro.codetest.backend.repository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import com.digpro.codetest.backend.domain.Coordinate;
import com.digpro.codetest.backend.util.CoordinateConvertor;
import com.digpro.codetest.backend.util.HttpClient;
import com.digpro.codetest.backend.util.Parser;

/**
 * A {@link Coordinate} Repository/Datasource Adaptor's Implementation which is
 * used to get {@link Coordinate} Objects from DataSource (servlet)
 * 
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */

@Repository
public class CoordinateRepositryImpl implements BaseRepository {

    private static final Logger log = LoggerFactory.getLogger(CoordinateRepositryImpl.class);
	private HttpClient httpClient;
	private CoordinateConvertor convertor;
	private Parser parser;
	//Spring Environment object which holds all configurations values from the Config file
	private Environment env;
	
	@Autowired
	public CoordinateRepositryImpl(HttpClient httpClient, CoordinateConvertor convertor, Parser parser,Environment env) {
		super();
		this.httpClient = httpClient;
		this.convertor = convertor;
		this.parser = parser;
		this.env = env;
	}

	@Override
	public List<Coordinate> getAll() throws Exception {
		String servletUrl = env.getProperty("servlet.url");
		String coordinatePattern =  env.getProperty("coordinate.pattern");
		log.info("getting data from the servlet using httpClient");
		String servletResponse = httpClient.doGetRequest(servletUrl);
		log.info("parsing servlet's data and extarct coordinates portion using simple parser");
		String CoordiantesData =parser.findByPattern(servletResponse,coordinatePattern);
		int lengthOFHeaderLine = coordinatePattern.length();
		//we have to add+1 to truncate/remove new line character "\n" along with the header
		String CoordiantesDataWithoutHeader = CoordiantesData
				.substring(CoordiantesData.indexOf(coordinatePattern) + lengthOFHeaderLine + 1).trim();
		log.info("convert parser data  to  coordinates using Coordinates convertor");
		List<Coordinate> coordinates= convertor.convertToListOfCoordinates(CoordiantesDataWithoutHeader);
		return coordinates;
	}

}
