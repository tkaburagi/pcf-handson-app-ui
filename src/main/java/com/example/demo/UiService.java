package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UiService {

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public UiService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Value( "${api.url}" )
    private String apiUrl;

    public AppInfo getAppInfo() throws Exception {
        String result = this.restTemplate.getForObject(apiUrl, String.class);
        AppInfo a = this.objectMapper.readValue(result, AppInfo.class);
        return  a;
    }

    public Book[] getAllBooks() throws Exception {
        String result = this.restTemplate.getForObject(apiUrl + "/allbooks", String.class);
        Book[] bList = this.objectMapper.readValue(result, Book[].class);
        return  bList;
    }

    public Book getBookById(@RequestParam("id") String id) throws Exception {
        String targetUrl = UriComponentsBuilder.fromUriString(apiUrl + "/book").queryParam("id", id).build().toString();
        String result = this.restTemplate.getForObject(targetUrl, String.class);
        Book b = this.objectMapper.readValue(result, Book.class);
        return b;
    }

    @HystrixCommand(fallbackMethod = "executeFallback")
    public String dummy() {
        return this.restTemplate.getForObject(apiUrl + "/dummy", String.class);
    }

    public String executeFallback(Throwable e) {
        return "Not available";
    }
}
