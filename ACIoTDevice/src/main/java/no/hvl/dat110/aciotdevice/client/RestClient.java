package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;

import static no.hvl.dat110.aciotdevice.client.Configuration.HOST;
import static no.hvl.dat110.aciotdevice.client.Configuration.PORT;

public class RestClient {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String URL = "http://" + HOST + ":" + PORT;
    private static final String LOGPATH = "/accessdevice/log/";
    private static final String CODEPATH = "/accessdevice/code";
    private static final Gson gson = new Gson();
    private final OkHttpClient client;

    public RestClient() {
        this.client = new OkHttpClient();
    }

    public void doPostAccessEntry(String message) {

        RequestBody body = RequestBody.create(JSON, gson.toJson(new AccessMessage(message)));
        Request request = new Request.Builder().url(URL + LOGPATH).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            System.out.println("json from doPostAccessEntry: " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AccessCode doGetAccessCode() {

        Request request = new Request.Builder().url(URL + CODEPATH).get().build();

        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            System.out.println("json from doGetAccessCode: " + json);
            return gson.fromJson(json, AccessCode.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
