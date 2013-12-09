package resources.client;

import com.google.gwt.event.dom.client.*;

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
    String url="http://172.16.100.132:8080/chat-kata/api/chat";
    String num_seq, user_name;

    public HandlerChat(){
        num_seq="0";
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
        listener_server= new ListenerServer();
        listener_server.requestMessagesToTheServer(url,num_seq);
        sender_server=new SenderServer();
        sender_server.doPost(url,Login.getMsgUser(),Login.getUserName());
    }

}
