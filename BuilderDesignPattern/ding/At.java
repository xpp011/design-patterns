package BuilderDesignPattern.ding;

import java.util.List;

/**
 * @program: design-patterns
 * @description: at
 * @author: xpp011
 * @create: 2022-08-01 22:09
 **/

public class At {

    private List<String> atMobiles;

    private List<String> atUserIds;

    private Boolean isAtAll;

    private At(List<String> atMobiles, List<String> atUserIds, Boolean isAtAll) {
        this.atMobiles = atMobiles;
        this.atUserIds = atUserIds;
        this.isAtAll = isAtAll;
    }

    public static class Builder {

        private List<String> atMobiles;

        private List<String> atUserIds;

        private Boolean isAtAll;

        public Builder setAtMobiles(List<String> atMobiles) {
            this.atMobiles = atMobiles;
            return this;
        }

        public Builder setAtUserIds(List<String> atUserIds) {
            this.atUserIds = atUserIds;
            return this;
        }

        public Builder setAtAll(Boolean atAll) {
            isAtAll = atAll;
            return this;
        }

        public At builder() {
            return new At(atMobiles, atUserIds, isAtAll);
        }
    }


}
