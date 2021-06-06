package com.digpro.codetest.backend.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ConnectException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * 
 * @author Mutaz Hussein Kabashi
 * @see <a href="https://rieckpil.de/test-spring-webclient-with-mockwebserver-from-okhttp/"> MockWebServer tutorial </a>
 *
 */
public class OkHttpClientImplTest {

	private MockWebServer server;
	private HttpClient httpClient;
	private String url;

	@Before
	public void setup() throws IOException {
		server = new MockWebServer();
		server.url("/");
		httpClient = new OkHttpClientImpl();
		url = server.getHostName() + ":" + server.getPort();
	}

	@Test
	public void testDoGetRequestSuccess() throws IOException {
		server.enqueue(new MockResponse().setBody(getSampleResponse()));
		String response = httpClient.doGetRequest("http://" + url);
		assertEquals(response, getSampleResponse());
	}
	
	@Test
	public void testDoGetRequestFail() throws IOException {
		MockResponse mockResponse = new MockResponse()
			    .setResponseCode(500);
		server.enqueue(mockResponse);
		String response = httpClient.doGetRequest("http://" + url);
		assertEquals(response, getEmptyResponse());
	}
	
	@Test(expected = ConnectException.class)
	public void testDoGetRequestWithServerDown() throws IOException {
		server.shutdown();
		String response = httpClient.doGetRequest("http://" + url);
		assertEquals(response, getEmptyResponse());
	}

	@After
	public void tearDown() throws IOException {
		server.shutdown();
	}

	// util Methods
	// TODO sample response could be in proeries file , so that we could change the
	// test data without modifying test class
	private String getSampleResponse() {
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
	
	private String getEmptyResponse() {
		return "";
	}

}
