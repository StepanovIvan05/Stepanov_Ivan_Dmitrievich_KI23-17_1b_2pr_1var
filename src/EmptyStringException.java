/** Custom exception class for representing an exception when an empty string is encountered. */
public class EmptyStringException extends Exception {

  /**
   * Constructs a new EmptyStringException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the getMessage()
   *     method)
   */
  public EmptyStringException(String message) {
    super(message);
  }
}
