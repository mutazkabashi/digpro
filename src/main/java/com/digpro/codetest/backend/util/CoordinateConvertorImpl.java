package com.digpro.codetest.backend.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.digpro.codetest.backend.domain.Coordinate;

/**
 * 
 * @author Mutaz Hussein Kabashi
 * @version1.0
 */

@Component
public class CoordinateConvertorImpl implements CoordinateConvertor {
	
    private static final Logger LOG = LoggerFactory.getLogger(CoordinateConvertorImpl.class);


	@Override
	public Coordinate convertToCoordinate(String s) {
		if(s==null) {
			LOG.error("Error Occured white parsing data, Data is null",new NullPointerException());
			//TODO Exception message should be in config file 
			throw new  NullPointerException("convertToCoordinate() method's parameter is null");
		}
		String[] record = s.split(",");
		Coordinate coordinate = new Coordinate(Double.valueOf(record[0]), Double.valueOf(record[1]), record[2]);
		return coordinate;
	}

	@Override
	public List<Coordinate> convertToListOfCoordinates(String s) {
		if(s==null) {
			LOG.error("Error Occured white parsing data, Data is null",new NullPointerException());
			//TODO Exception message should be in config file 
			throw new  NullPointerException("convertToListOfCoordinates() method's parameter is null");
		}
		String[] records = s.split("\n");
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		for (String record : records) {
			coordinates.add(convertToCoordinate(record));
		}
		return coordinates;
	}

}
