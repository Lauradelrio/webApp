package resources.client.Presenter;

import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 5/12/13
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class SenderServer {
    public static void doPost(String url, String msg, String user) {
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
        Request response;

        try {
            builder.setHeader("Content-Type", "application/json");
            msg= msgToFormatJson(msg,user);
            response = builder.sendRequest(msg, new RequestCallback() {

                public void onError(Request request, Throwable exception) {
                    HandlerChat.errorWithServer("Error: Message can't be delivered");
                }

                public void onResponseReceived(Request request, Response response) {
                    HandlerChat.errorWithServer("Delivered");
                }
            });
        } catch (RequestException e) {}
    }

    private static String msgToFormatJson(String msg, String user){
       return "{\"nick\": \"" + user + "\", \"message\": \"" + msg + "\"}";
    }
}
