package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import extractor.HTMLExtractor;

public class HTMLExtractorTest {
  HTMLExtractor extrct;
  String expected = "<div class=\"g-section\"><div style=\"margin-top: 1em; "
      + "text-align: center; font-size: 1em;\"><div style=\"margin-bottom: 1em;font-style: italic\">";

  @Before
  public void setUp() throws Exception {
    extrct = new HTMLExtractor();
  }

  /**
   * Tests if HTMLExtractor getHTML method changes given URL from type HTML to
   * type String.
   * 
   * @throws Exception
   */
  @Test
  public void testLocalFile() throws Exception {
    String url = "sample3.html";
    String result = extrct.getHTML(url);
    assertEquals(expected, result);
  }

  /**
   * Tests if invalid file is caught
   * 
   * @throws Exception
   */
  @Test
  public void testInvalidFileCatch() throws Exception {
    String url = "sample3";
    String result = extrct.getHTML(url);
    assertEquals(null, result);

  }


}
