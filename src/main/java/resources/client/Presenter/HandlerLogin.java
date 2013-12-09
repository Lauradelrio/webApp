package resources.client.Presenter;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.RootPanel;
import resources.shared.FieldVerifier;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 5/12/13
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */

public class HandlerLogin implements ClickHandler, KeyUpHandler {

    String user_name;
    String user_password;
    IHandler interface_login;

    public HandlerLogin(IHandler login){
        interface_login=login;

    }

    public void onClick(ClickEvent event) {
        interface_login.setErrorLogin("Error in Username or Password");
        sendNameToChat();
    }

    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            sendNameToChat();
        }
    }

    private void sendNameToChat() {
        interface_login.addHandlerChat();
        interface_login.setErrorLogin("");
        user_name= interface_login.getUserName();
        user_password= interface_login.getPasswordName();
        interface_login.setTitleChat(user_name);
        if (!FieldVerifier.isValidName(user_name, user_password)) {
            interface_login.setErrorLogin("Error in Username or Password");
        }else{
            RootPanel.get("block").setVisible(false);
            RootPanel.get("option").setVisible(false);
            RootPanel.get("chat").setVisible(true);
        }
    }
}
