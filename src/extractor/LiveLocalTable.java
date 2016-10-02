package extractor;

import java.util.Hashtable;

/**
 * @author Raj
 * Table of Regular Expressions. 
 */
public class LiveLocalTable {
  
  // Hashtable of regex for local html files
  public Hashtable<String, String> localMatches =
      new Hashtable<String, String>();
  // Hashtable of regex for live html files
  public Hashtable<String, String> liveMatches =
      new Hashtable<String, String>();

  /**
   * table of regex for use if local html file is given
   */
  public void localTable() {
    localMatches.put("AuthorsName", "<span id=\"cit-name-"
        + "display\" class=\"cit-in-place-nohover\">(.*?)</span>");

    localMatches.put("AllCitations",
        "<td class=\"cit-borderleft cit-data\">([0-9]*?)</td>");

    localMatches.put("FirstThreePubs",
        "class=\"cit-dark-large-link\">(.*?)</a>");

    localMatches.put("FivePubsCites", "cites=([0-9]*?)\">([0-9]*?)</a>");

    localMatches.put("Coauthors",
        "<a class=\"cit-dark-link\" "
        + "href=\"(.*?)=en\" title=\"(.*?)\">(.*?)</a>");
  }

  /**
   * table of regex for use if live html file is given
   */
  public void liveTable() {
    liveMatches.put("AuthorsName", "<div id=\"gsc_prf_in\">(.*?)</div>");
    
    liveMatches.put("AllCitations", 
        "<td class=\"gsc_rsb_std\">([0-9]*?)</td>");

    liveMatches.put("FirstThreePubs", "class=\"gsc_a_at\">(.*?)</a>");

    liveMatches.put("FivePubsCites",
        "<a href=\"https:(.*?)\" class=\"gsc_a_ac\">([0-9]*?)</a>");

    liveMatches.put("Coauthors",
        "<a class=\"gsc_rsb_aa\" href=\"(.*?)\">(.*?)</a>");
  }

}
