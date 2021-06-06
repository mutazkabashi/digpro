package com.digpro.codetest.backend.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.digpro.codetest.backend.domain.Coordinate;

/**
 * 
 * @author Mutaz Hussein Kabashi
 *
 */
public class CoordinateConvertorImplTest {
	private CoordinateConvertor convertor;
	
	@Before
	public void setup() {
		convertor = new CoordinateConvertorImpl();
	}

	@Test
	public void testConvertToCoordinateValidRecord() {
		String record = getRecord();
		Coordinate coordinate = convertor.convertToCoordinate(record);
		Coordinate expectedCoordinate = getExpectedResult();
		assertEquals(coordinate, expectedCoordinate);
	}
	
	@Test
	public void testconvertToListOfCoordinatesValidRecords() {
		String records = getRecords();
		List<Coordinate> coordinates = convertor.convertToListOfCoordinates(records);
		List<Coordinate> expectedCoordinates = getExpectedResultList();
	    assertTrue(coordinates.size() == expectedCoordinates.size() && coordinates.containsAll(expectedCoordinates) && expectedCoordinates.containsAll(coordinates));
		//assertTrue(IsTwoListOfCorrdinatesAreEquals(coordinates, expectedCoordinates));

	}
	
	@Test(expected = NullPointerException.class)
	public void testConvertToCoordinateNullRecord() {
		String record = null;
		Coordinate coordinate = convertor.convertToCoordinate(record);
	}
	
	@Test(expected = NullPointerException.class)
	public void testConvertToListOfCoordinatesNullRecords() {
		String records = null;
		List<Coordinate> coordinates = convertor.convertToListOfCoordinates(records);
	}
	
	// utils methods

		private String getRecord() {
			String result = "-25, 718, ST-843";
			return result;
		}
		
		private Coordinate getExpectedResult() {
			Coordinate coordinate =  new Coordinate(-25D, 718D, "ST-843");
			return coordinate;
		}
		
		private String getRecords() {
			String result ="-25, 718, ST-843\n" + "580, 298, ST-833\n" + "143, -206, ST-661\n"
					+ "652, 605, ST-540\n"  + "472, -164, ST-519";
			return result;
		}
		
		private List<Coordinate> getExpectedResultList() {
			List<Coordinate> coordinates = new ArrayList<Coordinate>();
			Coordinate coordinate1 =  new Coordinate(-25D, 718D, "ST-843");
			Coordinate coordinate2 =  new Coordinate(580D, 298D, "ST-833");
			Coordinate coordinate3 =  new Coordinate(143D, -206D, "ST-661");
			Coordinate coordinate4 =  new Coordinate(652D, 605D, "ST-540");
			Coordinate coordinate5 =  new Coordinate(472D, -164D, "ST-519");
			
			coordinates.add(coordinate1);
			coordinates.add(coordinate2);
			coordinates.add(coordinate3);
			coordinates.add(coordinate4);
			coordinates.add(coordinate5);
			return coordinates;
		}
		
		//TODO this method could be moved to Util class
		private boolean IsTwoListOfCorrdinatesAreEquals(List<Coordinate> listOne, List<Coordinate> ListTwo) {
			for (Coordinate coordinate : ListTwo) {
				if(!listOne.contains(coordinate)) {
					return false;
				}
			}
			return true;
		}

}
