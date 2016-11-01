package com.univtln.b00dle.client.utilities.network.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Project client.
 * Package com.univtln.b00dle.client.utilities.network.api.
 * File API.java.
 * Created by Ysee on 01/11/2016 - 14:45.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class API {
    private static final Logger LOGGER = Logger.getLogger(API.class);
    /**
     * Token to access restricted resources.
     * Header Authorization: "...."
     */
    public static String authorization = "";

    /**
     * Base url.
     */
    private static final String URL = "http://localhost:3000/api";

    /**
     * Get the token authoization for restricted resources.
     */
    public final static String LOGIN = URL + "/login/auth_token";

    /**
     * All resources from the B00DLE API.
     */
    public static class Resources {
        /**
         * HTTP Resources to create user.
         * <p>
         * HTTP:POST
         * Header Content-Type: application/json
         * {
         * "name": "user_name",
         * "email": "user_email",
         * "password": "abcd1234",
         * "password_confirmation": "abcd1234"
         * }
         */
        public final static String USERS = URL + "/users";

        /**
         * HTTP Resources to manage opinion polls.
         * <p>
         * HTTP:POST
         * Header Content-Type: application/json
         * Header Authorization: TOKEN From LOGIN Request. See jwt.
         * {
         * "title": "zedzed",
         * "description": "Test description",
         * "place": "MY PLace...",
         * "emails": ["yseemonnier@gmail.com", "zed@gmail.com"],
         * "time_slots": [{
         * "from": "2016-11-15 15:30",
         * "to": "2016-11-15 17:00"
         * },{
         * "from": "2016-11-16 12:30",
         * "to": "2016-11-26 13:00"
         * }]
         * }
         * <p>
         * HTTP:GET Get all opinion polls
         * Header Authorization: TOKEN from LOGIN Request. See jwt.
         * <p>
         * HTTP:GET /:id Get specific opinion poll.
         * <p>
         * HTTP:UPDATE /:id Update specific opinion poll.
         * Header Content-Type: application/json
         * Header Authorization: TOKEN from LOGIN Request. See jwt.
         * Same parameters than POST request.
         * <p>
         * HTTP:DELETE /:id Delete specific opinion poll.
         * Header Authorization: TOKEN from LOGIN Request. See jwt.
         */
        public final static String OPINION_POLLS = URL + "/opinion_polls";

        /**
         * HTTP Resources to manage answers.
         * <p>
         * HTTP:POST Create a new answer.
         * Header Content-Type: application/json
         * {
         * "app_id": "5E:FF:56:A2:AF:15",
         * "name": "John",
         * "opinion_poll_id": 18,
         * "times": [14]
         * }
         * <p>
         * HTTP:UPDATE /:id update a specific answer.
         * Header Content-Type: application/json
         * {
         * "app_id": "5E:FF:56:A2:AF:15",
         * "times": [14, 15]
         * }
         */
        public final static String ANSWERS = URL + "/answers";
    }

    public static HttpResponse post(String url, String parameters) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        setHeader(request);
        request.setEntity(new StringEntity(parameters));
        LOGGER.debug("HTTP:POST - url: " + url);
        LOGGER.debug("HTTP:POST - parameters: " + parameters);
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw e;
        }
    }

    public static HttpResponse get(String url) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        setHeader(request);

        LOGGER.debug("HTTP:GET - url: " + url);
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw e;
        }
    }

    public static HttpResponse update(String url, String parameters) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut request = new HttpPut(url);
        setHeader(request);
        request.setEntity(new StringEntity(parameters));

        LOGGER.debug("HTTP:PUT - url: " + url);
        LOGGER.debug("HTTP:PUT - parameters: " + parameters);
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw e;
        }
    }

    public static HttpResponse delete(String url) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete(url);
        setHeader(request);

        LOGGER.debug("HTTP:DELETE - url: " + url);
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Set header properties.
     * @param http, request we want to add some header properties.
     */
    private static void setHeader(HttpRequestBase http) {
        http.setHeader("Content-Type", "application/json");
        http.setHeader("Authorization", authorization);
    }
}
