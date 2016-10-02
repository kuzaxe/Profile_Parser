
package driver;

import java.util.ArrayList;
import java.util.List;

import exception.InvalidArgsException;
import extractor.HTMLReader;
import output.ConsoleOutput;
import output.FileOutput;

/**
 * Program parses through given Google Scholar webpages to extract pre-specified
 * information. Returns to console or to specified outfile.
 * 
 * @author Raj
 */
public class MyParser {

  protected static HTMLReader reader = new HTMLReader();

  /**
   * Executes main program.
   * 
   * @param args
   */
  public static void main(String[] args) {
    List<String> output = new ArrayList<String>();
    try {
      // validate if arguments are correct.
      isValid(args);

      // create list of individual webpages to analyze.
      String inputFiles[] = args[0].split(",");
      // executes HTMLReader which returns a list of all data.
      boolean isValidFile = false;
      for (String URL : inputFiles) {
        List<String> data = reader.Execute(URL);
        if (data != null) {
          output.addAll(data);
          isValidFile = true;
        }
      }
       // adds the list of all co-authors alphabetically sorted at the end
      if (isValidFile) {
        output = reader.createAllCoauthorList();
      }
      
      
      if (args.length == 2) {
        // send to FileOutput class to write data into file.
        FileOutput.Execute(args[1], output);
      } else {
        // send to ConsoleOutput class to print data to console.
        ConsoleOutput.Execute(output);
      }

      // End - Try:Catch.
    } catch (InvalidArgsException e) {
      System.out.println(e.toString());
    } catch (Exception e) {
      System.out.println("Unexpected error occured in MyParser.");
    }
  }

  /**
   * Validates that number of arguments are correct.
   * 
   * @param args: list of given arguments
   * @throws InvalidArgsException
   */
  private static void isValid(String[] args) throws InvalidArgsException {
    // if arguments exceed length of two, throw error.
    if (args.length > 2) {
      throw new InvalidArgsException(": Invalid argument provided.");
    }
  }
}


