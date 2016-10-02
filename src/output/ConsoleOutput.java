package output;

import java.util.List;

/**
 * Utilized for printing data to console.
 * 
 * @author Raj
 */
public class ConsoleOutput {

  /**
   * Prints output onto console.
   * 
   * @param output
   */
  public static void Execute(List<String> output) {
    for (String line : output) {
      System.out.println(line);
    }
  }

}
