package com.learn.pact.producer;

public class PactModel {

    private final long id;
    private final String content;

    public PactModel(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}