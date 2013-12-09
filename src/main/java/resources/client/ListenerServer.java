package resources.client;

import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

import java.util.ArrayList;

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
//        final JSONObject msg_json = new JSONObject();
//        String message, next_seq;
//        JSONArray messages_json = new JSONArray();

        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {




//                        response.getText().toString());
                        /*try {
                            messages_json = json.getJSONArray("messages");
                            if(messages_arraylist.length()!=0){
                                for(int i=0;i<messages_arraylist.length();i++){
                                    JSONObject c = messages_arraylist.getJSONObject(i);
                                    msg_list.add(new Message(c.getString("nick"), c.getString("message")));
                                }
                                nextSeq = json.getInt("nextSeq");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
