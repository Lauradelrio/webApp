package resources.client.View;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.*;
import resources.client.Messages;
import resources.client.Presenter.HandlerChat;
import resources.client.Presenter.HandlerLogin;
import resources.client.Presenter.IHandler;


import java.util.ArrayList;


public class LoginChat implements EntryPoint, IHandler{

    private final Messages messages;
    private Button login_button, sendMsg_Button, logout_button;
    private TextBox userName_field, msgUser_Field;
    private PasswordTextBox password_field;
    private Label errorLogin_label, titleUserChat_Label, errorChat_label;
    private TextCell msg_TextCell;
    private CellList<String> msgListChat_CellList;
    private int countCell;
    private HandlerLogin handler_login;
    private HandlerChat handler_chat;
    private ScrollPanel msgList_scrollpanel;


    public LoginChat() {
        messages = GWT.create(Messages.class);
        login_button = new Button( messages.loginButton() );
        userName_field = new TextBox();
        password_field = new PasswordTextBox();
        errorLogin_label = new Label();
        msgUser_Field = new TextBox();
        sendMsg_Button = new Button(messages.sendButton());
        msg_TextCell = new TextCell();
        msgListChat_CellList = new CellList<String>(msg_TextCell);
        titleUserChat_Label = new Label();
        errorChat_label = new Label();
        countCell=0;
        msgList_scrollpanel= new ScrollPanel();
        logout_button = new Button( messages.logoutButton());
    }


    public void onModuleLoad() {


      // Create the login view
      userName_field.getElement().setAttribute("placeholder",messages.nameField());
      password_field.getElement().setAttribute("placeholder",messages.passwordField());
      RootPanel.get("nameFieldContainer").add(userName_field);
      RootPanel.get("passwordFieldContainer").add(password_field);
      RootPanel.get("loginButtonContainer").add(login_button);
      RootPanel.get("errorLoginLabelContainer").add(errorLogin_label);
      userName_field.setFocus(true);
      userName_field.selectAll();


    // Create the chat view
      msgList_scrollpanel.add(msgListChat_CellList);
      msgList_scrollpanel.addStyleName("scroll");
      RootPanel.get("titleUserChatContainer").add(titleUserChat_Label);
      RootPanel.get("msgListChatContainer").add(msgList_scrollpanel);
      RootPanel.get("msgUserFieldContainer").add(msgUser_Field);
      RootPanel.get("sendMsgButtonContainer").add(sendMsg_Button);
      RootPanel.get("errorChatLabelContainer").add(errorChat_label);
      RootPanel.get("logOutButtonContainer").add(logout_button);
      RootPanel.get("chat").setVisible(false);


    handler_login = new HandlerLogin(this);
    login_button.addClickHandler(handler_login);
    userName_field.addKeyUpHandler(handler_login);
    password_field.addKeyUpHandler(handler_login);
  }


    public void setErrorLogin(String error){
        errorLogin_label.setText(error);
    }

    public String getUserName(){
        return userName_field.getText().toString();
    }

    public String getPasswordName(){
        return password_field.getText().toString();
    }

    public void setTitleChat(String name){
        titleUserChat_Label.setText("CHAT KATA - "+ name);
    }

    public String getMsgUser(){
        return  msgUser_Field.getText().toString();
    }

    public void setMsgUser(String msg){
        msgUser_Field.setText(msg);
    }

    public void setMsgList(ArrayList<String> msg_list){
        countCell += msg_list.size();
        msgListChat_CellList.setPageSize(countCell);
        msgListChat_CellList.setRowCount(countCell,true);
        msgListChat_CellList.setRowData(countCell-msg_list.size(),msg_list);
        countCell += msg_list.size();
        msgList_scrollpanel.setVerticalScrollPosition(msgList_scrollpanel.getMaximumVerticalScrollPosition());
    }

    public void setErrorChat(String error){
        errorChat_label.setText(error);
    }

    public void addHandlerChat(){
        handler_chat = new HandlerChat(this);
        sendMsg_Button.addClickHandler(handler_chat);
        msgUser_Field.addKeyUpHandler(handler_chat);
        logout_button.addClickHandler(handler_chat);
    }

    public void logout(){
        userName_field.setText("");
        password_field.setText("");
        countCell=0;
        RootPanel.get("chat").setVisible(false);
        RootPanel.get("block").setVisible(true);
    }

    public Widget getButtonSend(){
        return sendMsg_Button;
    }

    public Widget getButtonLogout(){
        return logout_button;
    }


}
