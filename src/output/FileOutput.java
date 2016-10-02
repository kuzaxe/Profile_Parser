package output;

import java.io.*;
import java.util.List;

/**
 * Utilized for writing data into file.
 * 
 * @author Raj
 */
public class FileOutput {

  /**
   * Writes output into file with name "fileName"
   * 
   * @param fileName: user-defined fileName
   * @param output: list of data
   * @throws Exception
   */
  public static void Execute(String fileName, List<String> output)
      throws Exception {
    File fileObj = new File(fileName);
    // Clear contents of file, if file exists.
    new FileOutputStream(fileName).close();
    FileWriter file = new FileWriter(fileObj, true);
    for (String line : output) {
      file.write(line + System.getProperty("line.separator"));
    }
    file.flush();
    file.close();
  }

}
