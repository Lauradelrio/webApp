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


public class Login implements EntryPoint{


    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private final Messages messages;
    private final Button login_button;
    private static TextBox userName_field;
    private static PasswordTextBox password_field;
    private static Label error_label;
    private static TextBox msgUser_Field;
    private final Button sendMsg_Button;
    private static TextCell msg_TextCell;
    private static CellList<String> msgListChat_CellList;
    private static Label titleUserChat_Label;
    private static ArrayList<String> msgList_arraylist;


    public Login() {
        messages = GWT.create(Messages.class);
        login_button = new Button( messages.loginButton() );
        userName_field = new TextBox();
        password_field = new PasswordTextBox();
        error_label = new Label();
        msgUser_Field = new TextBox();
        sendMsg_Button = new Button(messages.loginButton());
        msg_TextCell = new TextCell();
        msgListChat_CellList = new CellList<String>(msg_TextCell);
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

    HandlerLogin handler_login = new HandlerLogin();
    HandlerChat handler_chat = new HandlerChat();
    login_button.addClickHandler(handler_login);
    userName_field.addKeyUpHandler(handler_login);
    password_field.addKeyUpHandler(handler_login);
    sendMsg_Button.addClickHandler(handler_chat);
    msgUser_Field.addKeyUpHandler(handler_chat);
  }

 //TODO borrar
    public static void cambiarmensajeuser(String msg){
        msgUser_Field.setText(msg);
    }

    public static void setError(String error){
        error_label.setText(error);
    }

    public static String getUserName(){
        return userName_field.getText().toString();
    }

    public static String getPasswordName(){
        return password_field.getText().toString();
    }

    public static void setTitleChat(String name){
        titleUserChat_Label.setText("CHAT KATA - "+ name);
    }

    public static String getMsgUser(){
        return  msgUser_Field.getText().toString();
    }

    public static void setMsgUser(String msg){
        msgUser_Field.setText(msg);
    }

    public static void setMsgList(ArrayList<String> msg_list){
        msgListChat_CellList.setRowCount(msg_list.size(),true);
        msgListChat_CellList.setRowData(0,msg_list);
    }









}
