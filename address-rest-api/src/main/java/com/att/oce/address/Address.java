package com.att.oce.address;
import org.springframework.data.annotation.Id;

import com.att.oce.util.*;
/**
 * @author pg939j
 */
public class Address {

    static final int MAX_LENGTH_DESCRIPTION = 500;
    static final int MAX_LENGTH_TITLE = 100;

    @Id
    private String id;

    private String description;

    private String title;

    public Address() {}

    private Address(Builder builder) {
        this.description = builder.description;
        this.title = builder.title;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo[id=%s, description=%s, title=%s]",
                this.id,
                this.description,
                this.title
        );
    }

    /**
     * We don't have to use the builder pattern here because the constructed class has only two String fields.
     * However, I use the builder pattern in this example because it makes the code a bit easier to read.
     */
    public static class Builder {

        private String description;

        private String title;

        private Builder() {}

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Address build() {
        	Address build = new Address(this);


            return build;
        }
    }
    

}
