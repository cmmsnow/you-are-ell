package controllers;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient client;
    private MediaType mediaType;
    //private RequestBody json;

    public TransactionController() throws IOException {
        client = new OkHttpClient();//.newBuilder()
//                .connectTimeout(5, TimeUnit.MINUTES)
//                .readTimeout(5, TimeUnit.MINUTES)
//                .writeTimeout(5, TimeUnit.MINUTES)
//                .build();
        mediaType = MediaType.parse("application/json; charset=utf-8"); //Lake used ".get()"
        //json = RequestBody.create(mediaType, data);
    }

    public String get(String path) throws IOException {
        Request request = new Request.Builder()
                .url(rootURL + path)
                .method("GET", null)
                //.addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String postIds(String name, String github) throws IOException {
        String userid="-";
        String body="{\n" +
                "\n\t\"userid\": \"" + userid + "\"," +
                "\n\t\"name\": \"" + name + "\"," +
                "\n\t\"github\": \"" + github + "\"" +
                "    }";
        RequestBody json = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(rootURL + "/ids")
                .method("POST", json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        //String response = get("/ids");
        return response.body().string();
    }

    public String putIds(String userid, String name, String github) throws IOException {
        String body="{\n" +
                "        \"userid\": " + userid + ",\n" +
                "        \"name\": " + name + ",\n" +
                "        \"github\": " + github + "\n" +
                "    }";
        RequestBody json = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(rootURL + "/ids")
                .method("PUT", json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        //String response = get("/ids");
        return response.body().string();
    }

    public String postMessages(String fromId, String toId, String payload) throws IOException {
        String body="{\n" +
                "        \"sequence\": \"-\",\n" +
                "        \"timestamp\": \"2020-12-06T16:28:23.044849931Z\",\n" +
                "        \"fromid\": " + fromId + ",\n" +
                "        \"toid\": " + toId + ",\n" +
                "        \"message\": " + payload + "\n" +
                "    }";
        RequestBody json = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(rootURL + "/ids" + toId + "/messages")
                .method("POST", json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        //String response = get("/messages");
        return response.body().string();
    }
}

