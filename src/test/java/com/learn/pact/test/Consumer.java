package com.learn.pact.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Consumer {
    private String url;

    public Consumer(String url) {
        this.url = url;
    }

    public String getHelloWorld() throws IOException{
        HttpResponse response = getResponse("/hello-world");
        return getEntityAsString(response);
    }

    public int getResponseStatus() throws IOException{
        HttpResponse response = getResponse("/hello-world");
        return response.getStatusLine().getStatusCode();
    }

    private String getEntityAsString(HttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity());
    }

    public HttpResponse getResponse(String path) throws IOException {
        return Request.Get(url + path)
                .execute().returnResponse();
    }
}
