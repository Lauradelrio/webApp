package resources.client;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 5/12/13
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
public interface InterfaceHandler {
    public void setError(String error);
    public String getUserName();
    public String getPasswordName();
    public void setTitleChat(String name);
    public String getMsgUser();
    public void setMsgList(ArrayList<String> msg_list);
}
