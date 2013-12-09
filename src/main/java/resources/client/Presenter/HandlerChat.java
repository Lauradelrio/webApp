package resources.client.Presenter;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;
import resources.client.View.Login;

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
    private int user_name;

    public HandlerChat(){
        num_seq=0;


        Timer refreshTimer = new Timer() {
            @Override
            public void run() {
                listenerServer(num_seq);
            }
        };

        refreshTimer.scheduleRepeating(1000);
    }

    public void onClick(ClickEvent event) {

        sendMsgToServer();
    }

    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            sendMsgToServer();
        }
    }

    private void sendMsgToServer(){
        listenerServer(num_seq);
        sender_server=new SenderServer();
        sender_server.doPost(url, Login.getMsgUser(),Login.getUserName());
        Login.setMsgUser("");
    }

    private void listenerServer(int n_seq){
        listener_server= new ListenerServer();
        listener_server.requestMessagesToTheServer(url,Integer.toString(n_seq));
    }


    public static void errorWithServer(String msg){
        Login.setErrorChat(msg);
    }

    public static void setNumSeq(int n_seq){
       num_seq=n_seq;
    }


}
