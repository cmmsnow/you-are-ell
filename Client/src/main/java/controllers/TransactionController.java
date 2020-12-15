package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import okhttp3.*;
import java.io.IOException;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient client;
    private MediaType mediaType;

    public TransactionController() throws IOException {
        client = new OkHttpClient();
        mediaType = MediaType.parse("application/json; charset=utf-8");
    }

    public String get(String path) throws IOException {
        Request request = new Request.Builder()
                .url(rootURL + path)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String postIds(Id id) throws IOException {
        String body = new ObjectMapper().writeValueAsString(id);
        RequestBody json = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(rootURL + "/ids")
                .method("POST", json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String putIds(Id id) throws IOException {
        String body = new ObjectMapper().writeValueAsString(id);
        RequestBody json = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(rootURL + "/ids")
                .method("PUT", json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String postMessages(String toId, Message message) throws IOException {
        String body = new ObjectMapper().writeValueAsString(message);
        RequestBody json = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(rootURL + "/ids/" + toId + "/messages")
                .method("POST", json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

