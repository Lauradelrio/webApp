package resources.client;

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
    public static void doPost(String url, String postData) {
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);

        try {
            builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
            Request response = builder.sendRequest(postData, new RequestCallback() {

                public void onError(Request request, Throwable exception) {
                    // code omitted for clarity
                }

                public void onResponseReceived(Request request, Response response) {
                    // code omitted for clarity
                }
            });
        } catch (RequestException e) {
            Window.alert("Failed to send the request: " + e.getMessage());
        }
    }
}
