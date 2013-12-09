package resources.client.Model;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 9/12/13
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface IResponseFactory extends AutoBeanFactory {
    AutoBean<IResponse> response();
    AutoBean<IChatMessage> message(Message message);

}
