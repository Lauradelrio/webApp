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
        login.cambiarmensajeuser("Enviado");
        listener_server.requestMessagesToTheServer(url,num_seq);
    }

}
