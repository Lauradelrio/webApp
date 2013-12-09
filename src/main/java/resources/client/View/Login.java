package resources.client.View;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.*;
import resources.client.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import resources.client.Presenter.HandlerChat;
import resources.client.Presenter.HandlerLogin;

import java.util.ArrayList;


public class Login implements EntryPoint{


    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private final Messages messages;
    private final Button login_button, sendMsg_Button;
    private static TextBox userName_field, msgUser_Field;
    private static PasswordTextBox password_field;
    private static Label errorLogin_label, titleUserChat_Label, errorChat_label;
    private static TextCell msg_TextCell;
    private static CellList<String> msgListChat_CellList;
    private static ArrayList<String> msgList_arraylist;
    private static int countCell;


    public Login() {
        messages = GWT.create(Messages.class);
        login_button = new Button( messages.loginButton() );
        userName_field = new TextBox();
        password_field = new PasswordTextBox();
        errorLogin_label = new Label();
        msgUser_Field = new TextBox();
        sendMsg_Button = new Button(messages.loginButton());
        msg_TextCell = new TextCell();
        msgListChat_CellList = new CellList<String>(msg_TextCell);
        titleUserChat_Label = new Label();
        msgList_arraylist = new ArrayList<String>();
        errorChat_label = new Label();
        countCell=0;

    }


    public void onModuleLoad() {

      userName_field.getElement().setAttribute("placeholder",messages.nameField());
      password_field.getElement().setAttribute("placeholder",messages.passwordField());

//    loginButton.addStyleName("sendButton");

    RootPanel.get("nameFieldContainer").add(userName_field);
    RootPanel.get("passwordFieldContainer").add(password_field);
    RootPanel.get("loginButtonContainer").add(login_button);
    RootPanel.get("errorLoginLabelContainer").add(errorLogin_label);

      userName_field.setFocus(true);
      userName_field.selectAll();



    // Create the chat view
       RootPanel.get("titleUserChatContainer").add(titleUserChat_Label);
      RootPanel.get("msgListChatContainer").add(msgListChat_CellList);
      RootPanel.get("msgUserFieldContainer").add(msgUser_Field);
      RootPanel.get("sendMsgButtonContainer").add(sendMsg_Button);
      RootPanel.get("errorChatLabelContainer").add(errorChat_label);


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


    public static void setErrorLogin(String error){
        errorLogin_label.setText(error);
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

    //TODO cambiar a lista de message
    public static void setMsgList(ArrayList<String> msg_list){
        countCell += msg_list.size();
        msgListChat_CellList.setPageSize(countCell);
        msgListChat_CellList.setRowCount(countCell,true);
        msgListChat_CellList.setRowData(countCell-msg_list.size(),msg_list);
        countCell += msg_list.size();
    }

    public static void setErrorChat(String error){
        errorChat_label.setText(error);
    }









}
