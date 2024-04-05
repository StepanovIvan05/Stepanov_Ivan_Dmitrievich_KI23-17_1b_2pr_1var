/** Custom exception class for representing an exception when a negative value is encountered. */
public class NegativeValueException extends Exception {

  /**
   * Constructs a new NegativeValueException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the getMessage()
   *     method)
   */
  public NegativeValueException(String message) {
    super(message);
  }
}
