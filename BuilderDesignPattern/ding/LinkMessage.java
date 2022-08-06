package BuilderDesignPattern.ding;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-08-01 22:15
 **/

public class LinkMessage extends Message {
    public static class Builder extends MessageBuilder {

        private String text;

        private String title;

        private String picUrl;

        private String messageUrl;

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPicUrl(String picUrl) {
            this.picUrl = picUrl;
            return this;
        }

        public Builder setMessageUrl(String messageUrl) {
            this.messageUrl = messageUrl;
            return this;
        }

        public Builder setAt(At at) {
            this.at = at;
            return this;
        }

        @Override
        protected Message builderBody(final Message message) {
            message.put("text", text);
            message.put("title", title);
            message.put("picUrl", picUrl);
            message.put("messageUrl", messageUrl);
            return message;
        }

        @Override
        protected MessageType messageType() {
            return MessageType.LINK;
        }
    }

}
