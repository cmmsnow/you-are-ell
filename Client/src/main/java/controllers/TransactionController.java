package controllers;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient okHttpClient = new OkHttpClient();
    public TransactionController() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        //RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(rootURL)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }

}

