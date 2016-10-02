package exception;


/**
 * Invalid Arguments Exception for invalid arguments provided by user.
 * 
 * @returns message.
 */
public class InvalidArgsException extends Exception {

  private static final long serialVersionUID = 8748136694333934840L;
  protected String errorMessage;

  /**
   * Stores program defined error message.
   * 
   * @param errorMessage
   */
  public InvalidArgsException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * @returns a string representation of the error message to print to console.
   */
  public String toString() {
    return "Invalid Argument(s) Error: " + this.errorMessage;
  }
}
