package com.digpro.codetest.backend.util;

import java.util.List;

import com.digpro.codetest.backend.domain.Coordinate;

/**
 * A convertor which converts from Obejct (e,g String) to {@link Coordinate} object and vice versa
 * @author Mutaz Hussein Kabashi
 * @version1.0
 *
 */
public interface CoordinateConvertor {
	
	/**
	 * convert {@link String} to {@link Coordinate} object
	 * @param s
	 * @return Coordinate object
	 */
	public Coordinate convertToCoordinate(String s);
	
	/**
	 * convert {@link String} to {@link List} of {@link Coordinate} object
	 * @param s
	 * @return List of Coordinates
	 */
	public List<Coordinate> convertToListOfCoordinates(String s);

}
