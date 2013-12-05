package resources.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.*;
import resources.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class login implements EntryPoint {

  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  private final Messages messages = GWT.create(Messages.class);
  private String user_name;


  public void onModuleLoad() {
    final Button login_button = new Button( messages.loginButton() );
    final TextBox userName_field = new TextBox();
    final PasswordTextBox password_field = new PasswordTextBox();
    final Label error_label = new Label();



      userName_field.getElement().setAttribute("placeholder",messages.nameField());
      password_field.getElement().setAttribute("placeholder",messages.passwordField());

//    loginButton.addStyleName("sendButton");

    RootPanel.get("nameFieldContainer").add(userName_field);
    RootPanel.get("passwordFieldContainer").add(password_field);
    RootPanel.get("loginButtonContainer").add(login_button);
    RootPanel.get("errorLabelContainer").add(error_label);

      userName_field.setFocus(true);
      userName_field.selectAll();



    // Create the chat view

      final TextBox msgUser_Field = new TextBox();
      final Button sendMsg_Button = new Button();
      final TextCell message_TextCell = new TextCell();
      final CellList<String> msgListChat_CellList = new CellList<String>(message_TextCell);
      final Label titleUserChat_Label = new Label();

      RootPanel.get("titleUserChatContainer").add(titleUserChat_Label);
      RootPanel.get("msgListChatContainer").add(msgListChat_CellList);
      RootPanel.get("msgUserFieldContainer").add(msgUser_Field);
      RootPanel.get("sendMsgButtonContainer").add(sendMsg_Button);

      RootPanel.get("chat").setVisible(false);


    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
          login_button.setEnabled(true);
          login_button.setFocus(true);
      }
    });

    class MyHandler implements ClickHandler, KeyUpHandler {

      public void onClick(ClickEvent event) {

        sendNameToChat();
      }

      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToChat();
        }
      }



      private void sendNameToChat() {
          error_label.setText("");
          user_name=userName_field.getText().toString();
          titleUserChat_Label.setText("CHAT KATA - "+ user_name);
          if (!FieldVerifier.isValidName(user_name,password_field.getText().toString())) {
              error_label.setText("Error in Username or Password");
          }else{
              RootPanel.get("block").setVisible(false);
              RootPanel.get("option").setVisible(false);
              RootPanel.get("chat").setVisible(true);
          }
      }


      private void sendNameToServer() {
          error_label.setText("Pulsado boton");
        RootPanel.get("block").setVisible(false);
        String textToServer = userName_field.getText();
        if (!FieldVerifier.isValidName(textToServer,password_field.getText())) {
            error_label.setText("Please enter at least four characters");
          return;
        }

          login_button.setEnabled(false);
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
      }
    }

    MyHandler handler = new MyHandler();
      login_button.addClickHandler(handler);
    userName_field.addKeyUpHandler(handler);
//    password_Field.addKeyUpHandler(handler);
  }
}
