package exceptions;
public class ErabiltzaileaAlreadyExist extends Exception {
 private static final long serialVersionUID = 1L;
 
 public ErabiltzaileaAlreadyExist()
  {
    super();
  }
  /**This exception is triggered if the user already exist
  *@param s String of the exception
  */
  public ErabiltzaileaAlreadyExist(String s)
  {
    super(s);
  }
}
