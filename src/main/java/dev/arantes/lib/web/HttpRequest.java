package dev.arantes.lib.web;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class HttpRequest {
    private String url;
    private HttpMethod method;
    private Map<String, String> headers;
    private DefaultHttpClient httpClient;
    private int statusCode;
    private JSONObject bodyResponse;

    public HttpRequest(String url) {
        this.url = url;
        this.headers = new HashMap<>();
        this.method = HttpMethod.GET;
        httpClient = new DefaultHttpClient();
    }

    public HttpRequest(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
        this.headers = new HashMap<>();
        httpClient = new DefaultHttpClient();
    }

    public HttpRequest(String url, HttpMethod method, Map<String, String> headers) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        httpClient = new DefaultHttpClient();
    }

    public void addHeader(String key, String value){
        headers.put(key, value);
    }

    public void removeHeader(String key){
        headers.remove(key);
    }

    public void send(String path) throws IOException, ParseException {
        send(path, null);
    }

    public void send(String path, JSONObject data) throws IOException, ParseException {
        JSONObject toSend = new JSONObject();
        if (data != null){
            toSend.put("data", data);
        }

        switch (method){
            case POST:
                sendPOST(path, toSend);
                break;
            case GET:
                sendGET(path);
                break;
        }
    }

    private void sendGET(String path) throws IOException, ParseException {
        HttpGet request = new HttpGet(url + path);
        headers.forEach(request::addHeader);

        HttpResponse response = httpClient.execute(request);

        statusCode = response.getStatusLine().getStatusCode();
        HttpEntity httpEntity = response.getEntity();

        bodyResponse = (JSONObject) new JSONParser().parse(EntityUtils.toString(httpEntity));
    }

    private void sendPOST(String path, JSONObject data) throws IOException, ParseException {
        HttpPost request = new HttpPost(url + path);
        headers.forEach(request::addHeader);

        if (!headers.containsKey("content-type")) {
            request.addHeader("content-type", "application/json");
        }

        request.setEntity(new StringEntity(data.toString()));
        org.apache.http.HttpResponse response = httpClient.execute(request);

        statusCode = response.getStatusLine().getStatusCode();
        HttpEntity httpEntity = response.getEntity();

        bodyResponse = (JSONObject) new JSONParser().parse(EntityUtils.toString(httpEntity));
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JSONObject getBodyResponse() {
        return bodyResponse;
    }

    public void close(){
        httpClient.getConnectionManager().shutdown();
    }

}
