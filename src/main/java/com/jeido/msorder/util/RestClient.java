package com.jeido.msorder.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient<T> {

    private final String url;
    private final RestTemplate template;
    private final HttpHeaders headers;

    public RestClient(String url) {
        this.url = url;
        this.template = new RestTemplate();
        this.headers = new HttpHeaders();

        headers.add("accept", "*/*");
        headers.add("content-type", "application/json");
    }

    public T getRequest(Class<T> responseType) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<T> response = template.exchange(url, HttpMethod.GET, requestEntity, responseType);
        if (response.hasBody()) {
            return response.getBody();
        }
        return null;
    }


}
