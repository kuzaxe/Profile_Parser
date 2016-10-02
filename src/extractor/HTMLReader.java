package extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import output.OutputFormatter;

/**
 * README: - - - - - 
 * 
 * 
 * HTMLExtractor gets String test representation of HTML File.
 * HTMLMatcher gets wanted data from HTML File. 
 * And this, HTMLReader gets the exact data from the extracted matches. 
 * OutputFormatter gets the exact data and puts it in user-friendly format. 
 * MyParser puts this user-friendly format into file or onto console. 
 * 
 * This is my SRP Design. 
 * @author Raj
 *
 */
public class HTMLReader {

  public static HTMLMatcher matcher;
  private static List<String> outputResult;
  private static String HTMLFile;
  private static List<String> allMatches = new ArrayList<String>();
  private static List<String> CoAuthors = new ArrayList<String>();
  private static OutputFormatter format = new OutputFormatter();;
  private boolean isValidFile = false;

  /**
   * Given a url, program returns a list of all data extracted from the webpage
   * 
   * @param googleScholarURL
   * @return outputResults: List of Strings.
   */
  public List<String> Execute(String googleScholarURL) {
    try {
      // get String representation of HTML File.
      HTMLFile = getHTMLFile(googleScholarURL);
      if (HTMLFile != null) {
        matcher = new HTMLMatcher(HTMLFile);
        outputResult = new ArrayList<String>();

        // check if given url is live or local html url
        if (googleScholarURL.startsWith("https://")) {
          matcher.setLive(true);
        }

        // Execute all data extractor methods.
        extractAuthorsName();
        extractAllCitations();
        extracti10Index();
        extractFirstThreePubs();
        extractFirstFivePubsCites();
        extractNumberOfCoauthors();
      }
    } catch (Exception e) {
      System.out.println("Error has occured in HTMLReader class");
    }
    allMatches.clear();
    return outputResult;
  }


  /**
   * Gets a string representation of the HTML file given.
   * 
   * @param googleScholarURL
   * @return rawHTMLString: String
   * @throws Exception
   */
  private static String getHTMLFile(String googleScholarURL) throws Exception {
    HTMLExtractor googleScholarParser = new HTMLExtractor();
    String rawHTMLString = googleScholarParser.getHTML(googleScholarURL);
    return rawHTMLString;
  }

  /**
   * Extracts the authors name from HTML File.
   */
  public void extractAuthorsName() {
    try {
      // gets all matched objects for Author Name.
      Matcher matcherObject = matcher.patternMatcher("AuthorsName");

      // add name to outputResult
      while (matcherObject.find()) {
        format.Formatter(1, matcherObject.group(1));
        isValidFile = true;
      }

    } catch (Exception e) {
      System.out.println("Error has occured in extractAuthorsName method");
    }
  }

  /**
   * Extracts the "Citations - all" table value from HTML File.
   */
  private void extractAllCitations() {
    try {
      // gets all matched objects for extracting All Citations.
      Matcher matcherObject = matcher.patternMatcher("AllCitations");


      while (matcherObject.find()) {
        allMatches.add(matcherObject.group(1));
      }

      // add all citations from allMatches, add it to outputResult
      if (!allMatches.isEmpty()) {
        format.Formatter(2, allMatches.get(0));
      }
        

    } catch (Exception e) {
      System.out.println("Error has occured in extractAllCitations method");
    }
  }

  /**
   * Extracts the "i10 - Since 2009" table value from HTML File
   */
  private void extracti10Index() {
    try {
      // utilizes the previously found table values from extractAllCitations
      // and adds result to outputResult
      if (!allMatches.isEmpty()) {
        format.Formatter(3, allMatches.get(5));
      }
    } catch (Exception e) {
      System.out.println("Error has occured in extracti10Index method");
    }
  }

  /**
   * Extracts the name of the first three publications from HTML File
   */
  private void extractFirstThreePubs() {
    try {
      List<String> pubMatches = new ArrayList<String>();
      // gets all matched objects for extracting publication names.
      Matcher matcherObject = matcher.patternMatcher("FirstThreePubs");


      while (matcherObject.find()) {
        pubMatches.add(matcherObject.group(1));
      }

      // formats the output into desired style
      format.Formatter(4, pubMatches);

    } catch (Exception e) {
      System.out.println("Error has occured in extractFirstThreePubs method");
    }
  }

  /**
   * Extracts the total citations of the first file publications.
   */
  private void extractFirstFivePubsCites() {
    try {
      List<String> citeMatches = new ArrayList<String>();
      // gets all matched objects for extracting publication citations.
      Matcher matcherObject = matcher.patternMatcher("FivePubsCites");


      while (matcherObject.find()) {
        citeMatches.add(matcherObject.group(2));
      }

      int fiveCiteMatches;

      // adds the total according to if minimum five publications can be found
      if (citeMatches.size() < 5) {
        fiveCiteMatches = citeMatches.size();
      } else {
        fiveCiteMatches = 5;
      }

      if (citeMatches.size() != 0) {
        int count = 0;
        int total = 0;
        while (count < fiveCiteMatches) {
          total += Integer.parseInt(citeMatches.get(count));
          count++;
        }

        format.Formatter(5, total);

      }

    } catch (Exception e) {
      System.out
          .println("Error has occured in extractFirstFivePubsCites method");
    }
  }

  /**
   * Extracts the total number of coauthors on the HTML File
   */
  private void extractNumberOfCoauthors() {
    try {
      List<String> coAuthor = new ArrayList<String>();
      // gets all matched objects for extracting coauthor names.
      Matcher matcherObject = matcher.patternMatcher("Coauthors");

      String authorName;
      // check if name already exists in data structure.
      while (matcherObject.find()) {
        authorName = matcherObject.group(2).toString();
        coAuthor.add(authorName);
        if (!CoAuthors.contains(authorName)) {
          CoAuthors.add(authorName);
        }
      }
      if (isValidFile) {
        format.Formatter(6, coAuthor.size());
      }
      

    } catch (Exception e) {
      System.out.println("Error has occured in extractNumberOfCoauthors");
    }
  }

  public List<String> createAllCoauthorList() {
    if (isValidFile) {
      format.Formatter(7, CoAuthors);
      outputResult = format.getFinalOutput(); 
    }
    return outputResult;
  }

}
