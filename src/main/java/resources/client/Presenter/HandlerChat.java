package resources.client.Presenter;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import resources.client.View.LoginChat;
import com.google.gwt.user.client.Cookies;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 5/12/13
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class HandlerChat implements ClickHandler, KeyUpHandler {

    ListenerServer listener_server;
    SenderServer sender_server;
    String url="http://172.16.100.215:8080/chat-kata/api/chat";
    private static int num_seq;
    private static String user_name;
    static IHandler interface_login = new LoginChat();
    Widget sender;

    public HandlerChat(IHandler login){
        interface_login=login;
        user_name=interface_login.getUserName();
        try{ num_seq= Integer.parseInt(Cookies.getCookie(user_name));
        }catch (NumberFormatException e){num_seq=0;}
        Timer refreshTimer = new Timer() {
            @Override
            public void run() {
                listenerServer(num_seq);
            }
        };
        refreshTimer.scheduleRepeating(500);
    }

    public void onClick(ClickEvent event) {
        /*if(send) sendMsgToServer();
        else if (logout) logout();*/
        sender = (Widget) event.getSource();
        if (sender == interface_login.getButtonSend()) {
            sendMsgToServer();
        } else if (sender == interface_login.getButtonLogout()) {
            logout();
        }
    }

    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            sendMsgToServer();
        }
    }

    private void sendMsgToServer(){
        listenerServer(num_seq);
        sender_server=new SenderServer();
        sender_server.doPost(url, interface_login.getMsgUser(),interface_login.getUserName());
        interface_login.setMsgUser("");
    }

    private void listenerServer(int n_seq){
        listener_server= new ListenerServer();
        listener_server.requestMessagesToTheServer(url,Integer.toString(n_seq));
    }

    private void logout(){
        interface_login.logout();
    }


    public static void errorWithServer(String msg){
         interface_login.setErrorChat(msg);
    }

    public static void setMsgList(ArrayList<String> msg_string_list){
        interface_login.setMsgList(msg_string_list);
    }

    public static void setNumSeq(int num_s){
        num_seq=num_s;
        Cookies.setCookie(user_name, Integer.toString(num_s));
    }

}
