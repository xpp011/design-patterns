package BuilderPattern.ding;

public enum MessageType {

    TEXT("text"),
    LINK("link"),
    MARKDOWN("markdown"),
    ;

    private String type;

    public String getType() {
        return type;
    }

    MessageType(String type) {
        this.type = type;
    }

}
