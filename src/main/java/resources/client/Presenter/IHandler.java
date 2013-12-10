package resources.client.Presenter;

import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 5/12/13
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
public interface IHandler {
    public void setErrorLogin(String error);
    public String getUserName();
    public String getPasswordName();
    public void setTitleChat(String name);
    public String getMsgUser();
    public void setMsgUser(String msg);
    public void setMsgList(ArrayList<String> msg_list);
    public void setErrorChat(String error);
    public void addHandlerChat();
    public void logout();
    public Widget getButtonSend();
    public Widget getButtonLogout();
}
