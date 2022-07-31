package BuilderPattern.ding;

import java.util.HashMap;
import java.util.List;

/**
 * @program: design-patterns
 * @description:  钉钉机器人消息体
 * @author: xpp011
 * @create: 2022-07-31 22:47
 **/

public class Message extends HashMap<String, Object> {

    private Message() {
    }

    /**
     * 建造者模式，其他类型的消息体建造器全部继承该抽象类
     */
    public static abstract class Builder {

        /**
         * at的人
         */
        private AtBuilder at;

        public AtBuilder at() {
            return new AtBuilder();
        }

        /**
         * 构造消息体
         * @param message
         * @return {@link Message}
         */
        protected abstract Message builder(final Message message);

        /**
         * 消息类型
         * @return {@link Message}
         */
        protected abstract MessageType messageType();

        public Message builder() {
            Message message = new Message();
            message.put("msgtype", messageType().getType());
            builder(message);
            at.builder(message);
            return message;
        }

    }


    public static class TextBuilder extends Builder {

        private String content;

        public TextBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        @Override
        protected Message builder(final Message message) {
            message.put("content", content);
            return message;
        }

        @Override
        protected MessageType messageType() {
            return MessageType.TEXT;
        }
    }


    public static class LinkBuilder extends Builder {
        private String text;

        private String title;

        private String picUrl;

        private String messageUrl;

        public LinkBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public LinkBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public LinkBuilder setPicUrl(String picUrl) {
            this.picUrl = picUrl;
            return this;
        }

        public LinkBuilder setMessageUrl(String messageUrl) {
            this.messageUrl = messageUrl;
            return this;
        }

        @Override
        protected Message builder(final Message message) {
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


    public static class MarkDownBuilder extends Builder {

        private String title;

        private String text;

        public MarkDownBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public MarkDownBuilder setText(String text) {
            this.text = text;
            return this;
        }

        @Override
        protected Message builder(final Message message) {
            message.put("title", title);
            message.put("text", text);
            return message;
        }

        @Override
        protected MessageType messageType() {
            return MessageType.MARKDOWN;
        }
    }


    public static class AtBuilder {

        private List<String> atMobiles;

        private List<String> atUserIds;

        private Boolean isAtAll;

        public AtBuilder setAtMobiles(List<String> atMobiles) {
            this.atMobiles = atMobiles;
            return this;
        }

        public AtBuilder setAtUserIds(List<String> atUserIds) {
            this.atUserIds = atUserIds;
            return this;
        }

        public AtBuilder setAtAll(Boolean atAll) {
            isAtAll = atAll;
            return this;
        }

        protected Message builder(final Message message) {
            message.put("atMobiles", atMobiles);
            message.put("atUserIds", atUserIds);
            message.put("isAtAll", isAtAll);
            return message;
        }
    }

}
