/**
 * This code belongs to UTM Computer Science Department. Code was provided along
 * with the program. I, Raj Pandya, take no credit for this code. It is property
 * of University of Toronto - Computer Science & Mathematics Department. Thank
 * you.
 */

package extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLExtractor {

  /**
   * Extracts HTML content to String representation.
   * 
   * @param urlString
   * @return
   * @throws Exception
   */
  public String getHTML(String urlString) throws Exception {
    try {
      StringBuilder html = new StringBuilder();
      URL url;
      
      if (!urlString.startsWith("https://")) {
        url = new File(urlString).toURI().toURL();
      } else {
        url = new URL(urlString);
      }

      BufferedReader htmlbr =
          new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      while ((line = htmlbr.readLine()) != null) {
        html.append(line);
      }
      htmlbr.close();
      return html.toString();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return null;
  }

}
