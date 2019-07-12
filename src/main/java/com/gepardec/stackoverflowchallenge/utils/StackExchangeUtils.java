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

/**
 *
 * @author praktikant_ankermann
 */
public class StackExchangeUtils {

    private static final String BASE_URL = "http://api.stackexchange.com/2.2/";

    public static final String sendRequestAndGetJson(String resourceURL, String requestMethod) {
        BufferedReader in = null;

        try {
            in = createBufferedReader(resourceURL, requestMethod);
            
            return createJsonResponseFromReader(in);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }

    }

    private static String createJsonResponseFromReader(BufferedReader in) throws IOException {
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        return response.toString();
    }

    private static BufferedReader createBufferedReader(String resourceURL, String requestMethod) throws ProtocolException, IOException {
        HttpURLConnection con = openHttpURLConnection(resourceURL);
        con.setRequestMethod(requestMethod);
        return new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())));
    }

    private static HttpURLConnection openHttpURLConnection(String resourceURL) throws MalformedURLException, IOException {
        URL obj = new URL(BASE_URL + resourceURL);
        return (HttpURLConnection) obj.openConnection();
    }

}
