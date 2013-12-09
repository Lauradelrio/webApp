package resources.client.Model;

import resources.client.Model.IChatMessage;

/**
 * Created with IntelliJ IDEA.
 * User: laura
 * Date: 9/12/13
 * Time: 10:01
 * To change this template use File | Settings | File Templates.
 */
public class Message implements IChatMessage {
    private String message;
    private String nick;


    public Message(String nick, String message){
        this.message=message;
        this.nick=nick;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }



    public String getMessage() {
        return message;
    }

    public String getNick() {
        return nick;
    }

}
