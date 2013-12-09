package resources.client;

import com.google.gwt.http.client.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import resources.client.Model.IChatMessage;
import resources.client.Model.IResponse;
import resources.client.Model.IResponseFactory;
import resources.client.Model.Message;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;

import java.util.ArrayList;
import java.util.List;

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
        Request request;
//        final JSONObject msg_json = new JSONObject();
//        String message, next_seq;
//        JSONArray messages_json = new JSONArray();

        try {
            request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                  //  responseHandler.GETFail();
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        IResponse serverResponse = decodeJSON(response.getText());
                        GETSuccessful(serverResponse);
                    } else{}
                       // responseHandler.GETFail();
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }
    }


    private IResponse decodeJSON(String json) {
        IResponseFactory factory = GWT.create(IResponseFactory.class);
        AutoBean<IResponse> bean = AutoBeanCodex.decode(factory, IResponse.class, json);
        return bean.as();
    }

    public void GETSuccessful(IResponse response) {
        ArrayList<Message> msg_list = new ArrayList<Message>();
        ArrayList<String> msg_string_list = new ArrayList<String>();

       /* ChatState chatState = ChatState.getChatState();

        //coge el nex_seq de response
        chatState.setNextSeq(response.getNextSeq());*/

        //lee msg de response
        List<IChatMessage> messages = response.getMessages();
        for(IChatMessage message : messages){
            msg_list.add(new Message(message.getNick(),message.getMessage()));
            msg_string_list.add(message.getMessage());
            //ChatState.getChatState().getMessages().add(new ChatMessage(message.getNick(), message.getMessage()));
        }
        Login.setMsgList(msg_string_list);
    }
}



