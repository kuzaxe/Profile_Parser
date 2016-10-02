package test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import org.junit.Before;

import extractor.HTMLMatcher;

public class HTMLMatcherTest {
  String LiveHtmlFile =
      "<td class=\"gsc_rsb_std\">576</td><td class=\"gsc_rsb_std\">"
          + "530</td></tr><tr><td class=\"gsc_rsb_sc1\">";
  String LocalHtmlFile =
      "<span id=\"cit-name-read\"><span id=\"cit-name-display\" class=\"cit-in-place-nohover\">Ola Spjuth</span></span>";

  HTMLMatcher match;

  @Before
  public void setUp() throws Exception {}

  /**
   * This test consists of: 1. utilizing LiveLocalTable (a constant hashtable)
   * 2. setting html type to Live mode. 3. testing if HTMLMatcher obtains all
   * data points from html file
   */
  @Test
  public void testObtainingContentFromLiveHTML() {
    match = new HTMLMatcher(LiveHtmlFile);
    match.setLive(true);
    Matcher matcher = match.patternMatcher("AllCitations");
    List<String> actual = new ArrayList<String>();
    while (matcher.find()) {
      actual.add(matcher.group(1));
    }
    List<String> expected = Arrays.asList("576", "530");
    assertEquals(expected, actual);
  }

  /**
   * This test consists of: 1. utilizing LiveLocalTable (a constant hashtable)
   * 2. setting html type to Live mode. 3. testing if HTMLMatcher obtains all
   * data points from html file
   */
  @Test
  public void testObtainingContentFromLocalHTML() {
    match = new HTMLMatcher(LocalHtmlFile);
    Matcher matcher = match.patternMatcher("AuthorsName");
    List<String> actual = new ArrayList<String>();
    while (matcher.find()) {
      actual.add(matcher.group(1));
    }
    List<String> expected = Arrays.asList("Ola Spjuth");
    assertEquals(expected, actual);
  }


}
