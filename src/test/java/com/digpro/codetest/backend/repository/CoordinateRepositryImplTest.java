package com.digpro.codetest.backend.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import com.digpro.codetest.backend.domain.Coordinate;
import com.digpro.codetest.backend.util.CoordinateConvertor;
import com.digpro.codetest.backend.util.HttpClient;
import com.digpro.codetest.backend.util.Parser;

/**
 * 
 * @author Mutaz Hussein Kabashi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CoordinateRepositryImplTest {

	@Mock
	private HttpClient httpClient;

	@Mock
	private CoordinateConvertor convertor;

	@Mock
	private Parser parser;

	@Mock
	private Environment env;

	private BaseRepository coordinateRepository;

	@Test
	public void testGetAllSuccess() throws Exception {
		assertMockObjectIsNotNull();
		when(env.getProperty("servlet.url"))
				.thenReturn("http://daily.digpro.se/bios/servlet/bios.servlets.web.RecruitmentTestServlet");
		when(env.getProperty("coordinate.pattern")).thenReturn("Data: (X, Y, Name)");
		when(httpClient.doGetRequest("http://daily.digpro.se/bios/servlet/bios.servlets.web.RecruitmentTestServlet"))
				.thenReturn(getHttpClientSampleResponse());
		when(parser.findByPattern(getHttpClientSampleResponse(), "Data: (X, Y, Name)"))
				.thenReturn(getParserSampleResponse());
		when(convertor.convertToListOfCoordinates(getConvertorSampleRequest()))
				.thenReturn(getConvertorSampleResponse());
		coordinateRepository = new CoordinateRepositryImpl(httpClient, convertor, parser, env);
		List<Coordinate> coordinates = coordinateRepository.getAll();
		List<Coordinate> expectedCoordinates = getConvertorSampleResponse();
		assertTrue(coordinates.size() == expectedCoordinates.size() && coordinates.containsAll(expectedCoordinates)
				&& expectedCoordinates.containsAll(coordinates));

	}

	@Test(expected = IOException.class)
	public void testGetAllThrowIOException() throws Exception {
		assertMockObjectIsNotNull();
		when(env.getProperty("servlet.url"))
				.thenReturn("http://daily.digpro.se/bios/servlet/bios.servlets.web.RecruitmentTestServlet");
		when(env.getProperty("coordinate.pattern")).thenReturn("Data: (X, Y, Name)");
		when(httpClient.doGetRequest("http://daily.digpro.se/bios/servlet/bios.servlets.web.RecruitmentTestServlet"))
				.thenThrow(new IOException());
		coordinateRepository = new CoordinateRepositryImpl(httpClient, convertor, parser, env);
		List<Coordinate> coordinates = coordinateRepository.getAll();

	}

	@Test(expected = NullPointerException.class)
	public void testGetAllThrowNullPpointerException() throws Exception {
		assertMockObjectIsNotNull();
		when(env.getProperty("servlet.url"))
				.thenReturn("http://daily.digpro.se/bios/servlet/bios.servlets.web.RecruitmentTestServlet");
		when(env.getProperty("coordinate.pattern")).thenReturn("Data: (X, Y, Name)");
		when(httpClient.doGetRequest("http://daily.digpro.se/bios/servlet/bios.servlets.web.RecruitmentTestServlet"))
				.thenReturn(getHttpClientSampleResponse());
		when(parser.findByPattern(getHttpClientSampleResponse(), "Data: (X, Y, Name)"))
				.thenThrow(new NullPointerException());
		coordinateRepository = new CoordinateRepositryImpl(httpClient, convertor, parser, env);
		List<Coordinate> coordinates = coordinateRepository.getAll();

	}

	// util methods

	private void assertMockObjectIsNotNull() {
		assertNotNull(httpClient);
		assertNotNull(convertor);
		assertNotNull(parser);
		assertNotNull(env);
	}

	private String getHttpClientSampleResponse() {
		String response = "# \n" + "# Recruitment Test\n" + "# \n"
				+ "# Write a Java Application (Java 8 or later) that:\n" + "# 0. Opens a window.\n"
				+ "# 1. Loads coordinates from a web server (this page! encoding is ISO-8859-1).\n"
				+ "# 2. Draws symbols using the coordinates as a map.\n"
				+ "# 3. Reloads the coordinates every 30 seconds (clear old symbols and redraw with new data).\n"
				+ "# 4. Has a button for manually trigger reload.\n"
				+ "# 5. Has some way to disable the automatic reloading from the application.\n"
				+ "# 6. Shows the name of each coordinate as a tooltip for that point\n"
				+ "# 7. Shows some status while the application communicates with the server.\n"
				+ "# 8. Has an exit-button for closing the application.\n"
				+ "# 9. Has an about-dialog with contact information to you.\n"
				+ "# A. The application should be a single executable jar file.\n" + "# \n"
				+ "# How the application looks is not as important as how the code looks and works.\n" + "#\n"
				+ "# Data: (X, Y, Name)\n" + "682, -385, STÖ-1371\n" + "553, 1272, ST-1073\n" + "-82, 704, ST-1152\n"
				+ "498, 524, ST-700\n" + "-28, -251, STÖ-1449\n" + "76, 1220, ST-773\n" + "-20, 393, ST-791\n"
				+ "351, 97, ST-1177\n" + "-130, 706, ST-1464\n" + "-61, -380, ST-1146";
		return response;

	}

	private String getParserSampleResponse() {
		String result = "Data: (X, Y, Name)\n" + "-25, 718, ST-843\n" + "580, 298, ST-833\n" + "143, -206, ST-661\n"
				+ "652, 605, ST-540\n" + "-10, 38, ST-1183\n" + "615, 1173, ST-1323\n" + "410, 707, ST-1146\n"
				+ "-232, 138, ST-1031\n" + "472, -164, ST-519";
		return result;
	}

	private String getConvertorSampleRequest() {
		String coordinatePattern = "Data: (X, Y, Name)";
		int lengthOFHeaderLine = coordinatePattern.length();
		String result = getParserSampleResponse()
				.substring(getParserSampleResponse().indexOf("Data: (X, Y, Name)") + lengthOFHeaderLine + 1);
		return result;

	}

	private List<Coordinate> getConvertorSampleResponse() {
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
