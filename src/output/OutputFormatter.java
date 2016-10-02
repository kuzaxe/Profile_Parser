package output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;


public class OutputFormatter {
  List<String> finalOutput = new ArrayList<String>();
  String splitLine = "-----------------------------------"
      + "------------------------------------";
  String indent = "        ";
  
  public void Formatter(int code, String result) {

    // author name
    if (code == 1) {
      finalOutput.add(splitLine);
      finalOutput.add("1. Name of Author:");
      finalOutput.add(indent + StringEscapeUtils.unescapeHtml3(result));
    }
    // all citations
    if (code == 2) {
      finalOutput.add("2. Number of All Citations:");
      finalOutput.add(indent + result);
    }
    // i10 index citations
    if (code == 3) {
      finalOutput.add("3. Number of i10-index after 2009:");
      finalOutput.add(indent + result);
    }
  }

  public void Formatter(int code, List<String> result) {

    // first three publications
    if (code == 4) {

      int count = 0;
      if (result.size() != 0) {
        int pubMatchSize;
        if (result.size() < 3) {
          pubMatchSize = result.size();
        } else {
          pubMatchSize = 3;
        }
        finalOutput
            .add("4. Title of the first " + pubMatchSize + " publications:");

        while (count < pubMatchSize) {
          finalOutput
              .add(indent + (count + 1) + "-   " + StringEscapeUtils.unescapeHtml3(result.get(count)));
          count++;
        }
      }
    }

    // total list of coauthors.
    if (code == 7) {
      List<String> coAuthors = result;
      int size = coAuthors.size();
      // sort names alphabetically.
      Collections.sort(coAuthors);
      // format output.
      finalOutput.add("");
      finalOutput.add(splitLine);
      finalOutput.add("7. Co-Author list sorted (Total: " + size + "):");
      for (String author : coAuthors) {
        finalOutput.add(StringEscapeUtils.unescapeHtml3(author));
      }
    }
  }

  public void Formatter(int code, int value) {
    // total first five citations
    if (code == 5) {
      finalOutput.add("5. Total paper citation (first 5 papers):");
      finalOutput.add(indent + value);
    }
    // total number of co-authors
    if (code == 6) {
      finalOutput.add("6. Total Co-Authors:");
      finalOutput.add(indent + value);
    }
  }

  public List<String> getFinalOutput() {
    return finalOutput;
  }
  
}
