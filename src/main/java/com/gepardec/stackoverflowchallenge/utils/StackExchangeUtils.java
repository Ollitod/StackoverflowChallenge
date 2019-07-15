/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.eclipse.persistence.internal.oxm.record.json.JSONReader;

/**
 *
 * @author praktikant_ankermann
 */
public class StackExchangeUtils {

    private static final String BASE_URL = "http://api.stackexchange.com/2.2/";
    private static final String REQUEST_PARAMS = "?order=desc&sort=reputation&site=stackoverflow";

    public static final String sendRequestAndGetJson(String resourceURL, String requestMethod) {
        BufferedReader in = null;
        JsonObject o;
        JsonReader r;
        try {
            in = createBufferedReader(resourceURL, requestMethod);
            r = Json.createReader(in);
            o = r.readObject();
            if (o.containsKey("error_id") || o.getJsonArray("items").isEmpty()) {
                return null;
            } else {
                return o.toString();
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                return null;
            }
        }

    }

    private static BufferedReader createBufferedReader(String resourceURL, String requestMethod) throws ProtocolException, IOException {
        HttpURLConnection con = openHttpURLConnection(resourceURL);
        con.setRequestMethod(requestMethod);
        return new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())));
    }

    private static HttpURLConnection openHttpURLConnection(String resourceURL) throws MalformedURLException, IOException {
        URL obj = new URL(BASE_URL + resourceURL + REQUEST_PARAMS);
        return (HttpURLConnection) obj.openConnection();
    }

}
