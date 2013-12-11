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
    private Button submit, send_msg_Button, logout_button;
    private TextBox name_field, msg_user_Field;
    private PasswordTextBox password_field;
    private Label error_login_label, title_user_chat_Label, error_chat_label;
    private TextCell msg_textcell;
    private CellList<String> msg_list_chat_celllist;
    private int countCell;
    private HandlerLogin handler_login;
    private HandlerChat handler_chat;
    private ScrollPanel msg_list_scrollpanel;
    private Label user;   //use: icon field
    private Label pass;   //use: icon field
    private HandlerRegistration handler_send_msg_button, handler_msg_user_field, handler_logout_msg_button;


    public LoginChat() {
        messages = GWT.create(Messages.class);

        user = new Label("p");
        name_field = new TextBox();
        pass = new Label("k");
        password_field = new PasswordTextBox();
        submit = new Button( "a" );
        error_login_label = new Label();

        msg_user_Field = new TextBox();
        send_msg_Button = new Button(messages.sendButton());
        msg_textcell = new TextCell();
        msg_list_chat_celllist = new CellList<String>(msg_textcell);
        title_user_chat_Label = new Label();
        error_chat_label = new Label();
        countCell=0;
        msg_list_scrollpanel= new ScrollPanel();
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

        error_login_label.getElement().setClassName("error_text");

        RootPanel.get("nameFieldContainer").add(user);
        RootPanel.get("nameFieldContainer").add(name_field);
        RootPanel.get("passwordFieldContainer").add(pass);
        RootPanel.get("passwordFieldContainer").add(password_field);
        RootPanel.get("loginButtonContainer").add(submit);
        RootPanel.get("errorLoginLabelContainer").add(error_login_label);

        RootPanel.get().addStyleName("body");

        // Create the chat view
        title_user_chat_Label.getElement().setClassName("p");

        msg_list_scrollpanel.add(msg_list_chat_celllist);
        msg_list_scrollpanel.addStyleName("scroll");
        RootPanel.get("titleUserChatContainer").addStyleName("head_chat");
        msg_user_Field.getElement().setClassName("input_message");
        RootPanel.get("msgListChatContainer").addStyleName("msg_list");
        send_msg_Button.getElement().setClassName("send");
        logout_button.getElement().addClassName("logout_button");

        RootPanel.get("errorChatLabelContainer").addStyleName("warning");
        RootPanel.get("titleUserChatContainer").add(title_user_chat_Label);
        RootPanel.get("msgListChatContainer").add(msg_list_scrollpanel);
        RootPanel.get("msgUserFieldContainer").add(msg_user_Field);
        RootPanel.get("sendMsgButtonContainer").add(send_msg_Button);
        RootPanel.get("errorChatLabelContainer").add(error_chat_label);
        RootPanel.get("logOutButtonContainer").add(logout_button);
        RootPanel.get("chat").setVisible(false);

        handler_login = new HandlerLogin(this);
        submit.addClickHandler(handler_login);
        name_field.addKeyUpHandler(handler_login);
        password_field.addKeyUpHandler(handler_login);
    }


    public void setErrorLogin(String error){
        error_login_label.setText(error);
    }

    public String getUserName(){
        return name_field.getText().toString();
    }

    public String getPasswordName(){
        return password_field.getText().toString();
    }

    public void setTitleChat(String name){
        title_user_chat_Label.setText("CHAT KATA - "+ name);
    }

    public String getMsgUser(){
        return  msg_user_Field.getText().toString();
    }

    public void setMsgUser(String msg){
        msg_user_Field.setText(msg);
    }

    public void setMsgList(ArrayList<String> msg_list){
        countCell += msg_list.size();
        msg_list_chat_celllist.setPageSize(countCell);
        msg_list_chat_celllist.setRowCount(countCell,true);
        msg_list_chat_celllist.setRowData(countCell-msg_list.size(),msg_list);
        countCell += msg_list.size();
        msg_list_scrollpanel.setVerticalScrollPosition(msg_list_scrollpanel.getMaximumVerticalScrollPosition());
    }

    public void setErrorChat(String error){
        error_chat_label.setText(error);
    }

    public void addHandlerChat(){
        handler_chat = new HandlerChat(this);
        handler_send_msg_button = send_msg_Button.addClickHandler(handler_chat);
        handler_msg_user_field = msg_user_Field.addKeyUpHandler(handler_chat);
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
        return send_msg_Button;
    }

    public Widget getButtonLogout(){
        return logout_button;
    }


}