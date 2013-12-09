package resources.client.Presenter;

import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Cookies;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import resources.client.Model.IChatMessage;
import resources.client.Model.IResponse;
import resources.client.Model.IResponseFactory;
import resources.client.Model.Message;
import com.google.gwt.core.shared.GWT;

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
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url_get));
        Request request;

        try {
            request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    HandlerChat.errorWithServer("Error: Conexion server");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        HandlerChat.errorWithServer("");
                        IResponse serverResponse = decodeJSON(response.getText());
                        ToProcessResponse(serverResponse);
                    }  else HandlerChat.errorWithServer("Error: It don't recive message");
                }
            });
        } catch (RequestException e) {}
    }


    private IResponse decodeJSON(String json) {
        IResponseFactory factory = GWT.create(IResponseFactory.class);
        AutoBean<IResponse> bean = AutoBeanCodex.decode(factory, IResponse.class, json);
        return bean.as();
    }

    public void ToProcessResponse(IResponse response) {
        ArrayList<Message> msg_list = new ArrayList<Message>();
        ArrayList<String> msg_string_list = new ArrayList<String>();

        int num_s = response.getNextSeq();

        HandlerChat.setNumSeq(num_s);

        List<IChatMessage> messages = response.getMessages();
        if(!messages.isEmpty())  {
            for(IChatMessage message : messages){
                msg_list.add(new Message(message.getNick(),message.getMessage()));
                msg_string_list.add(message.getNick()+":\t"+message.getMessage());
            }
            HandlerChat.setMsgList(msg_string_list);
        }
    }
}



