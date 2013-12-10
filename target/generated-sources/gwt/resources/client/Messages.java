package resources.client;

/**
 * Interface to represent the messages contained in resource bundle:
 * 	/home/laura/Repositorios/webApp/src/main/resources/resources/client/Messages.properties'.
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {
  
  /**
   * Translated "Close Cerrar".
   * 
   * @return translated "Close Cerrar"
   */
  @DefaultMessage("Close Cerrar")
  @Key("closeButton")
  String closeButton();

  /**
   * Translated "Login".
   * 
   * @return translated "Login"
   */
  @DefaultMessage("Login")
  @Key("loginButton")
  String loginButton();

  /**
   * Translated "Logout".
   * 
   * @return translated "Logout"
   */
  @DefaultMessage("Logout")
  @Key("logoutButton")
  String logoutButton();

  /**
   * Translated "Enter your Username".
   * 
   * @return translated "Enter your Username"
   */
  @DefaultMessage("Enter your Username")
  @Key("nameField")
  String nameField();

  /**
   * Translated "Enter your Password".
   * 
   * @return translated "Enter your Password"
   */
  @DefaultMessage("Enter your Password")
  @Key("passwordField")
  String passwordField();

  /**
   * Translated "a".
   * 
   * @return translated "a"
   */
  @DefaultMessage("a")
  @Key("sendButton")
  String sendButton();
}
