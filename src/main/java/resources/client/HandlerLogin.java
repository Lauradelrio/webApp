package resources.client;

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

class HandlerLogin implements ClickHandler, KeyUpHandler {

    String user_name;
    String user_password;
    //InterfaceHandler interface_login = new Login();

    public void onClick(ClickEvent event) {

        sendNameToChat();
    }

    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            sendNameToChat();
        }
    }

    private void sendNameToChat() {
        Login.setError("");
        user_name= Login.getUserName();
        user_password= Login.getPasswordName();
        Login.setTitleChat(user_name);
        if (!FieldVerifier.isValidName(user_name, user_password)) {
            Login.setError("Error in Username or Password");
        }else{
            RootPanel.get("block").setVisible(false);
            RootPanel.get("option").setVisible(false);
            RootPanel.get("chat").setVisible(true);
        }
    }


   /* private void sendNameToServer() {

        String textToServer = userName_field.getText();

        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");
        greetingService.greetServer(textToServer, new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                dialogBox.setText("Remote Procedure Call - Failure");
                serverResponseLabel.addStyleName("serverResponseLabelError");
                serverResponseLabel.setHTML(SERVER_ERROR);
                dialogBox.center();
                closeButton.setFocus(true);
            }

            public void onSuccess(String result) {
                dialogBox.setText("Remote Procedure Call");
                serverResponseLabel.removeStyleName("serverResponseLabelError");
                serverResponseLabel.setHTML(result);
                dialogBox.center();
                closeButton.setFocus(true);
            }
        });
    }*/
}
