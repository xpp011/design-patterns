package ChainOfResponsibilityDesignPattern.demo;

import java.time.LocalDateTime;

/**
 * 内容
 *
 * @author: xpp011 2022-10-05 23:28
 **/

public class Context {

    private String author;

    private LocalDateTime postDateTime;

    private String contextType;

    private String context;

    private String replyContextId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(LocalDateTime postDateTime) {
        this.postDateTime = postDateTime;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getReplyContextId() {
        return replyContextId;
    }

    public void setReplyContextId(String replyContextId) {
        this.replyContextId = replyContextId;
    }
}
