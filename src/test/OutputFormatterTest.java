package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import output.OutputFormatter;

public class OutputFormatterTest {
  OutputFormatter format;
  List<String> actual;
  List<String> expected;

  @Before
  public void setUp() throws Exception {
    format = new OutputFormatter();
    actual = new ArrayList<String>();
    expected = new ArrayList<String>();

  }

  /**
   * Tests whether the author name, total citations, and i10 index values are
   * properly formatted. Also tests if getFinalOutput returns correct variable.
   */
  @Test
  public void testFirstPartitionOfOutputFormatter() {
    format.Formatter(1, "John Deerfield");
    format.Formatter(2, "100");
    format.Formatter(3, "10");

    actual = format.getFinalOutput();
    expected = Arrays.asList(
        "-----------------------------------------------------------------------",
        "1. Name of Author:", "        John Deerfield",
        "2. Number of All Citations:", "        100",
        "3. Number of i10-index after 2009:", "        10");
    assertEquals(expected, actual);
  }

  /**
   * Tests whether the three publications and the final authors list are
   * properly formatted. Also tests if getFinalOutput returns correct variable.
   */
  @Test
  public void testSecondPartitionOfOutputFormatter() {
    List<String> pubs = Arrays.asList("First pub", "Second pub");
    List<String> authors = Arrays.asList("Bob", "Frank", "Judy");
    format.Formatter(4, pubs);
    format.Formatter(7, authors);

    actual = format.getFinalOutput();
    expected = Arrays.asList("4. Title of the first 2 publications:",
        "        1-   First pub", "        2-   Second pub", "",
        "-----------------------------------------------------------------------",
        "7. Co-Author list sorted (Total: 3):", "Bob", "Frank", "Judy");
    assertEquals(expected, actual);
  }

  /**
   * Tests whether the number of first five citations and number of coauthors
   * are properly formatted. Also tests if getFinalOutput returns correct
   * variable.
   */
  @Test
  public void testThirdPartitionOfOutputFormatter() {
    format.Formatter(5, 200);
    format.Formatter(6, 5);

    actual = format.getFinalOutput();
    expected = Arrays.asList("5. Total paper citation (first 5 papers):",
        "        200", "6. Total Co-Authors:", "        5");
    assertEquals(expected, actual);
  }
}
