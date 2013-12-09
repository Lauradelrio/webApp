package resources.client.Model;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 9/12/13
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
import com.google.web.bindery.autobean.shared.AutoBean;


public interface IChatMessage {
    @AutoBean.PropertyName(value="message")
    String getMessage();
    @AutoBean.PropertyName(value="message")
    void setMessage(String message);

    @AutoBean.PropertyName(value="nick")
    String getNick();
    @AutoBean.PropertyName(value="nick")
    void setNick(String nick);

}
