package com.digpro.codetest.backend.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.digpro.codetest.backend.util.Parser;
import com.digpro.codetest.backend.util.SimpleParserImpl;

/**
 * 
 * @author Mutaz Hussein Kabashi
 *
 */
public class SimpleParserImplTest {
	private Parser parser;

	@Before
	public void setup() {
		parser = new SimpleParserImpl();

	}

	@Test
	public void testFindByPatternContainString() {
		String text = getText();
		String pattern = "Data: (X, Y, Name)";
		String result = parser.findByPattern(text, pattern);
		String expectedResult = getExpectedResult();
		//assertTrue(result.equals(expectedResult));
		assertEquals(result, expectedResult);
	}

	@Test
	public void testFindByPatternNotContainString() {
		String text = getText();
		String pattern = "No Exist String";
		String result = parser.findByPattern(text, pattern);
		String expectedResult = getExpectedResult();
		//assertFalse(result.equals(expectedResult));
		assertNotEquals(result, expectedResult);
	}

	@Test(expected = NullPointerException.class)
	public void testFindByPatternWithTextParametrsIsNull() {
		String text = null;
		String pattern = "Data: (X, Y, Name)";
		String result = parser.findByPattern(text, pattern);
		//String expectedResult = getExpectedResult();
	}

	@Test(expected = NullPointerException.class)
	public void testFindByPatternWithPatternParametrsIsNull() {
		String text = getText();
		String pattern = null;
		String result = parser.findByPattern(text, pattern);
		//String expectedResult = getExpectedResult();
	}

	// utils methods

	public String getText() {
		String text = "# \n" + "# Recruitment Test\n" + "# \n" + "# Write a Java Application (Java 8 or later) that:\n"
				+ "# 0. Opens a window.\n"
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
				+ "# Data: (X, Y, Name)\n" + "-25, 718, ST-843\n" + "580, 298, ST-833\n" + "143, -206, ST-661\n"
				+ "652, 605, ST-540\n" + "-10, 38, ST-1183\n" + "615, 1173, ST-1323\n" + "410, 707, ST-1146\n"
				+ "-232, 138, ST-1031\n" + "472, -164, ST-519\n" + "\n" + "";
		return text;
	}

	public String getExpectedResult() {
		String result = "Data: (X, Y, Name)\n" + "-25, 718, ST-843\n" + "580, 298, ST-833\n" + "143, -206, ST-661\n"
				+ "652, 605, ST-540\n" + "-10, 38, ST-1183\n" + "615, 1173, ST-1323\n" + "410, 707, ST-1146\n"
				+ "-232, 138, ST-1031\n" + "472, -164, ST-519";
		return result;
	}

}
