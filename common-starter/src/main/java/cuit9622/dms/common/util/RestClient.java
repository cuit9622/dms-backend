package cuit9622.dms.common.util;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient<Q, P> {
    private final RestTemplate rest;
    private final HttpHeaders headers;

    public RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public P get(String url,Class<P> clz) {
        HttpEntity<Q> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<P> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, clz);
        return responseEntity.getBody();
    }

    public P post(String url, Q json, Class<P> clz) {
        HttpEntity<Q> requestEntity = new HttpEntity<>(json, headers);
        ResponseEntity<P> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, clz);
        return responseEntity.getBody();
    }
}
