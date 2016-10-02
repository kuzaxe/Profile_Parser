package extractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Matches a given regex string with given HTML file. Returns matched objects. 
 * @author Raj
 */
public class HTMLMatcher {
  
  private boolean isLive = false;
  private String HTMLFile;
  private LiveLocalTable table = new LiveLocalTable();
  
  /**
   * Sets up environment for HTMLMatcher.
   * @param HTMLFile
   */
  public HTMLMatcher(String HTMLFile) {
    this.HTMLFile = HTMLFile;
    table.localTable();
    table.liveTable();
  }
  
  /**
   * Returns all matched strings from HTML file with respect to the given regex
   * @param Regex 
   * @return Matched Objects in HTML File.
   */
  public Matcher patternMatcher(String key) {
    String Regex;
    if (isLive) {
      Regex = table.liveMatches.get(key);
    } else {
      Regex = table.localMatches.get(key);
    }
   
    Pattern patternObject = Pattern.compile(Regex);
    Matcher matcherObject = patternObject.matcher(HTMLFile);
    return matcherObject;
  }

  /**
   * Sets isLive true iff given arguments are live html urls. 
   * @param isLive
   */
  public void setLive(boolean isLive) {
    this.isLive = isLive;
  }
  
}
