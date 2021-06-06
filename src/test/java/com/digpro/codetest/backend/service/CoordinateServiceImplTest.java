package com.digpro.codetest.backend.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import com.digpro.codetest.backend.domain.Coordinate;
import com.digpro.codetest.backend.repository.BaseRepository;
import com.digpro.codetest.backend.repository.CoordinateRepositryImpl;

/**
 * 
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CoordinateServiceImplTest {

	@Mock
	private BaseRepository coordinateRepository;

	private CoordinateService coordinateService;

	@Test
	public void testGetNewCoordinates() throws Exception {
		assertMockObjectIsNotNull();
		when(coordinateRepository.getAll())
		.thenReturn(getAllResponse());
		coordinateService = new CoordinateServiceImpl(coordinateRepository);
		List<Coordinate> coordinates =coordinateRepository.getAll();
		List<Coordinate> expectedCoordinates = getAllResponse();
		assertTrue(coordinates.size() == expectedCoordinates.size() && coordinates.containsAll(expectedCoordinates)
				&& expectedCoordinates.containsAll(coordinates));
	}
	
	@Test(expected = Exception.class)
	public void testGetAllThrowIOException() throws Exception {
		assertMockObjectIsNotNull();
		when(coordinateRepository.getAll())
		.thenThrow(new Exception());
		coordinateService = new CoordinateServiceImpl(coordinateRepository);
		List<Coordinate> coordinates =coordinateRepository.getAll();

	}

	// util methods

	private void assertMockObjectIsNotNull() {
		assertNotNull(coordinateRepository);
	}
	
	private List<Coordinate> getAllResponse() {
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		Coordinate coordinate1 = new Coordinate(-25D, 718D, "ST-843");
		Coordinate coordinate2 = new Coordinate(580D, 298D, "ST-833");
		Coordinate coordinate3 = new Coordinate(143D, -206D, "ST-661");
		Coordinate coordinate4 = new Coordinate(652D, 605D, "ST-540");
		Coordinate coordinate5 = new Coordinate(472D, -164D, "ST-519");

		coordinates.add(coordinate1);
		coordinates.add(coordinate2);
		coordinates.add(coordinate3);
		coordinates.add(coordinate4);
		coordinates.add(coordinate5);
		return coordinates;
	}

}
