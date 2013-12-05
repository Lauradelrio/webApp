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

import java.util.ArrayList;


public class login implements EntryPoint {

  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  private final Messages messages;
  private String user_name;
    private final Button login_button;
    private final TextBox userName_field;
    private final PasswordTextBox password_field;
    private final Label error_label;
    private final TextBox msgUser_Field;
    private final Button sendMsg_Button;
    private final TextCell message_TextCell;
    private CellList<String> msgListChat_CellList;
    private final Label titleUserChat_Label;
    private ArrayList<String> msgList_arraylist;


    public login() {
        messages = GWT.create(Messages.class);
        login_button = new Button( messages.loginButton() );
        userName_field = new TextBox();
        password_field = new PasswordTextBox();
        error_label = new Label();
        msgUser_Field = new TextBox();
        sendMsg_Button = new Button(messages.loginButton());
        message_TextCell = new TextCell();
        msgListChat_CellList = new CellList<String>(message_TextCell);
        titleUserChat_Label = new Label();
        msgList_arraylist = new ArrayList<String>();
    }


    public void onModuleLoad() {

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
        msgList_arraylist.add("Hola");
        msgList_arraylist.add("que tal");
        msgList_arraylist.add("Esto es una prueba");
        msgListChat_CellList.setRowCount(msgList_arraylist.size(),true);
        msgListChat_CellList.setRowData(0,msgList_arraylist);

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
      }
    }

        class MyHandler2 implements ClickHandler, KeyUpHandler {

            ListenerServer listener_server = new ListenerServer();
            String url="http://172.16.100.45:8080/chat-kata/api/chat";
            String num_seq="0";

            public void onClick(ClickEvent event) {

                sendMsgToServer();
            }

            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendMsgToServer();
                }
            }

            private void sendMsgToServer(){
                msgUser_Field.setText("Enviado");
                listener_server.requestMessagesToTheServer(url,num_seq);
            }

        }

    MyHandler handler = new MyHandler();
    MyHandler2 handler2 = new MyHandler2();
    login_button.addClickHandler(handler);
    userName_field.addKeyUpHandler(handler);
    password_field.addKeyUpHandler(handler);
    sendMsg_Button.addClickHandler(handler2);
    msgUser_Field.addKeyUpHandler(handler2);
  }









}
