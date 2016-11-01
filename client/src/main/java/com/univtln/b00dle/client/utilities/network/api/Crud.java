package com.univtln.b00dle.client.utilities.network.api;

import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * Created by Ysee on 01/11/2016 - 15:07.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public interface Crud {
    HttpResponse post(String url, String parameters) throws IOException;
    
    HttpResponse get(String url) throws IOException;

    HttpResponse update(String url, String parameters) throws IOException;

    HttpResponse delete(String url) throws IOException;
}
