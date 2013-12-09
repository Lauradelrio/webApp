package resources.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.*;
import resources.client.Model.Message;
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



    private static FormPanel login_form;
    private final Messages messages;
    private final Button submit;
    private static Label user_label;
    private static TextBox name;
    private static PasswordTextBox password_field;
    private static Label errorLogin_label, errorChat_label;
    private static TextBox msgUser_Field;
    private final Button sendMsg_Button;
    private static TextCell msg_TextCell;
    private static CellList<String> msgListChat_CellList;
    private static Label titleUserChat_Label;
    private static ArrayList<String> msgList_arraylist;


    public Login() {
        messages = GWT.create(Messages.class);

        login_form = new FormPanel();
        RootPanel.get().add(login_form);
        submit = new Button( "a" );

        user_label = new Label("p");
        name = new TextBox();
        name.setName("name");

        password_field = new PasswordTextBox();


        errorLogin_label = new Label();
        errorChat_label = new Label();
        msgUser_Field = new TextBox();
        sendMsg_Button = new Button(messages.loginButton());
        msg_TextCell = new TextCell();
        msgListChat_CellList = new CellList<String>(msg_TextCell);
        titleUserChat_Label = new Label();
        msgList_arraylist = new ArrayList<String>();
    }


    public void onModuleLoad() {

      user_label.getElement().setAttribute("for","name");
      name.getElement().setAttribute("placeholder",messages.nameField());
      name.getElement().setAttribute("required","true");

      //name.getElement().setClassName("user_input");
      password_field.getElement().setAttribute("placeholder",messages.passwordField());
     // password_field.getElement().setClassName("pass_input");
      //login_button.getElement().setClassName("submit");

      RootPanel.get("nameFieldContainer").add(name);
      RootPanel.get("passwordFieldContainer").add(password_field);
      RootPanel.get("loginButtonContainer").add(submit);
      RootPanel.get("errorLoginLabelContainer").add(errorLogin_label);
//    loginButton.addStyleName("sendButton");
    RootPanel.getBodyElement().getStyle().clearClear();
    RootPanel.getBodyElement().getStyle().setBackgroundColor("#292931");
    RootPanel.getBodyElement().getStyle().setMargin(0, Style.Unit.PX);


    RootPanel.get("block").setStylePrimaryName("block");

      name.setFocus(true);
      name.selectAll();



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
      RootPanel.get("errorChatLabelContainer").add(errorChat_label);

      RootPanel.get("chat").setVisible(false);

    HandlerLogin handler_login = new HandlerLogin();
    HandlerChat handler_chat = new HandlerChat();
    submit.addClickHandler(handler_login);
    name.addKeyUpHandler(handler_login);
    password_field.addKeyUpHandler(handler_login);
    sendMsg_Button.addClickHandler(handler_chat);
    msgUser_Field.addKeyUpHandler(handler_chat);
  }

 //TODO borrar
    public static void cambiarmensajeuser(String msg){
        msgUser_Field.setText(msg);
    }

    public static void setError(String error){
        errorLogin_label.setText(error);
    }

    public static String getUserName(){
        return name.getText().toString();
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
        msgListChat_CellList.setRowCount(msg_list.size(),true);
        msgListChat_CellList.setRowData(0, msg_list);
    }









}
