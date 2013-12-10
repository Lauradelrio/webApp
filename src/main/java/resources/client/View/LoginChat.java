package resources.client.View;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.*;
import resources.client.Messages;
import resources.client.Presenter.HandlerChat;
import resources.client.Presenter.HandlerLogin;
import resources.client.Presenter.IHandler;


import java.util.ArrayList;


public class LoginChat implements EntryPoint, IHandler{

    private final Messages messages;
    private Button submit;
    private Button sendMsg_Button, logout_button;
    private TextBox name_field, msgUser_Field;
    private PasswordTextBox password_field;
    private Label errorLogin_label, titleUserChat_Label, errorChat_label;
    private TextCell msg_TextCell;
    private CellList<String> msgListChat_CellList;
    private int countCell;
    private HandlerLogin handler_login;
    private HandlerChat handler_chat;
    private ScrollPanel msgList_scrollpanel;
    private Label user;   //use: icon field
    private Label pass;   //use: icon field
    private HandlerRegistration handler_msg_user_field, handler_send_msg_button, handler_logout_msg_button;


    public LoginChat() {
        messages = GWT.create(Messages.class);

        user = new Label("p");
        name_field = new TextBox();
        pass = new Label("k");
        password_field = new PasswordTextBox();
        submit = new Button( "a" );
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
        user.getElement().setClassName("label_block");
        user.getElement().setAttribute("for","name");
        user.getElement().setId("user");

        name_field.getElement().setId("name");
        name_field.setName("name");
        name_field.getElement().setClassName("name");
        name_field.getElement().setAttribute("placeholder",messages.nameField());
        name_field.getElement().setAttribute("required","true");
        name_field.getElement().setAttribute("name","name");

        pass.getElement().setClassName("label_block");
        pass.addStyleName("pass");

        password_field.getElement().setAttribute("placeholder",messages.passwordField());

        submit.getElement().setAttribute("name","submit");
        submit.getElement().setId("submit");

        RootPanel.get("nameFieldContainer").add(user);
        RootPanel.get("nameFieldContainer").add(name_field);
        RootPanel.get("passwordFieldContainer").add(pass);
        RootPanel.get("passwordFieldContainer").add(password_field);
        RootPanel.get("loginButtonContainer").add(submit);
        RootPanel.get("errorLoginLabelContainer").add(errorLogin_label);


    // Create the chat view
      titleUserChat_Label.getElement().setClassName("p");

      msgList_scrollpanel.add(msgListChat_CellList);
      msgList_scrollpanel.addStyleName("scroll");

        RootPanel.get("titleUserChatContainer").addStyleName("head_chat");
        msgUser_Field.getElement().setClassName("input_message");
        RootPanel.get("msgListChatContainer").addStyleName("msg_list");
        sendMsg_Button.getElement().setClassName("send");
        logout_button.getElement().addClassName("logout_button");
        RootPanel.get("errorChatLabelContainer").addStyleName("warning");

      RootPanel.get("titleUserChatContainer").add(titleUserChat_Label);
      RootPanel.get("msgListChatContainer").add(msgList_scrollpanel);
      RootPanel.get("msgUserFieldContainer").add(msgUser_Field);
      RootPanel.get("sendMsgButtonContainer").add(sendMsg_Button);
      RootPanel.get("errorChatLabelContainer").add(errorChat_label);
      RootPanel.get("logOutButtonContainer").add(logout_button);
      RootPanel.get("chat").setVisible(false);


        handler_login = new HandlerLogin(this);
        submit.addClickHandler(handler_login);
        name_field.addKeyUpHandler(handler_login);
        password_field.addKeyUpHandler(handler_login);
  }


    public void setErrorLogin(String error){
        errorLogin_label.setText(error);
    }

    public String getUserName(){
        return name_field.getText().toString();
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
        handler_send_msg_button = sendMsg_Button.addClickHandler(handler_chat);
        handler_msg_user_field = msgUser_Field.addKeyUpHandler(handler_chat);
        handler_logout_msg_button = logout_button.addClickHandler(handler_chat);
    }

    public void logout(){
        name_field.setText("");
        password_field.setText("");
        countCell=0;
        handler_send_msg_button.removeHandler();
        handler_logout_msg_button.removeHandler();
        handler_msg_user_field.removeHandler();
        handler_chat.getRefreshTimer().cancel();
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
