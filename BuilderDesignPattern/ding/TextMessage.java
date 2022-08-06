package BuilderDesignPattern.ding;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-08-01 22:14
 **/

public class TextMessage extends Message {
    public static class Builder extends MessageBuilder {

        private String content;

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setAt(At at) {
            this.at = at;
            return this;
        }

        @Override
        protected Message builderBody(final Message message) {
            message.put("content", content);
            return message;
        }

        @Override
        protected MessageType messageType() {
            return MessageType.TEXT;
        }
    }
}
