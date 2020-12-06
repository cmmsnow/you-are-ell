package controllers;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient client;
    private MediaType mediaType;

    public TransactionController() throws IOException {
        client = new OkHttpClient();//.newBuilder()
//                .connectTimeout(5, TimeUnit.MINUTES)
//                .readTimeout(5, TimeUnit.MINUTES)
//                .writeTimeout(5, TimeUnit.MINUTES)
//                .build();
        mediaType = MediaType.parse("application/json; charset=utf-8");
        //mediaType = MediaType.get("application/json; charset=utf-8");
        //RequestBody body = RequestBody.create(mediaType, data);
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

    public String post(String path) throws IOException {
        String body="{\n" +
                "        \"userid\": \"-\",\n" +
                "        \"name\": \"Waterlicious\",\n" +
                "        \"github\": \"Yoyo\"\n" +
                "    }";
        RequestBody json = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(rootURL + path)
                .method("POST", json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String put(String path) throws IOException {
        String body="{\n" +
                "        \"userid\": \"b46ba467a94e24292ae392310315b72316c8523d\",\n" +
                "        \"name\": \"WhippedByThisCourse\",\n" +
                "        \"github\": \"Yoyo\"\n" +
                "    }";
        RequestBody json = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(rootURL + path)
                .method("PUT", json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}

