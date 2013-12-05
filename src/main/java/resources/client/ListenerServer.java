package resources.client;

import com.google.gwt.http.client.*;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 5/12/13
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class ListenerServer {
    public void requestMessagesToTheServer (String url,String num_seq) {
        String url_get = url+ "?next_seq="+num_seq;
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        /*try {
                            // Formar el objeto que contiene la información
                            oInfo = new JSONObject();
                            oInfo.put("nombre", new JSONString(nombre.getText()));
                            oInfo.put("dir1", new JSONString(dir1.getText()));

                        }
                        catch (Exception e) {

                        }*/
                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }
    }
}
