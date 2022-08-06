package BuilderDesignPattern.ding;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-08-01 22:12
 **/

public abstract class MessageBuilder {

    protected At at;

    protected abstract Message builderBody(final Message message);

    protected abstract MessageType messageType();

    public Message builder() {
        Message message = new Message();
        message.put("msgtype", messageType().getType());
        message = builderBody(message);
        message.put("at", at);
        return message;
    }
}
