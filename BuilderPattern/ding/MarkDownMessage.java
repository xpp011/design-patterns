package BuilderPattern.ding;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-08-01 22:17
 **/

public class MarkDownMessage extends Message {

    public static class Builder extends MessageBuilder{

        private String title;

        private String text;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setAt(At at) {
            this.at = at;
            return this;
        }

        @Override
        protected Message builderBody(final Message message) {
            message.put("title", title);
            message.put("text", text);
            return message;
        }

        @Override
        protected MessageType messageType() {
            return MessageType.MARKDOWN;
        }
    }

}
