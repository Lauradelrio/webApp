package resources.client;

/**
 * Interface to represent the messages contained in resource bundle:
 * 	/home/startic/IdeaProjects/webApp2/src/main/resources/resources/client/Messages.properties'.
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
   * Translated "Send".
   * 
   * @return translated "Send"
   */
  @DefaultMessage("Send")
  @Key("loginButton")
  String loginButton();

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
}
