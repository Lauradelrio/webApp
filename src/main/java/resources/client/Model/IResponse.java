package resources.client.Model;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 9/12/13
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
import com.google.web.bindery.autobean.shared.AutoBean;

import java.util.List;

public interface IResponse {
    @AutoBean.PropertyName(value="messages")
    List<IChatMessage> getMessages();
    @AutoBean.PropertyName(value="nextSeq")
    int getNextSeq();

    @AutoBean.PropertyName(value="messages")
    void setMessages(List<IChatMessage> messages);
    @AutoBean.PropertyName(value="nextSeq")
    void setNextSeq(int nextSeq);
}
